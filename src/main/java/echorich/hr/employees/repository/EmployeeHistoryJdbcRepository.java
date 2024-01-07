package echorich.hr.employees.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import echorich.hr.employees.domain.EmployeeSalaryHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Repository
public class EmployeeHistoryJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public Integer saveAll(final List<EmployeeSalaryHistory> employeeSalaryHistories) {
		String sql =
			"INSERT INTO employee_salary_history"
				+ "(before_salary, created_date, employee_id) "
				+ "VALUES (?, ?, ?)";

		int[] results = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				EmployeeSalaryHistory employeeSalaryHistory = employeeSalaryHistories.get(i);
				ps.setBigDecimal(1, employeeSalaryHistory.getBeforeSalary());
				ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
				ps.setLong(3, employeeSalaryHistory.getEmployee().getId());
			}

			@Override
			public int getBatchSize() {
				return employeeSalaryHistories.size();
			}
		});

		return results.length;
	}
}