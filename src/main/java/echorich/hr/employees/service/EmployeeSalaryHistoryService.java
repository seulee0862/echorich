package echorich.hr.employees.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import echorich.hr.employees.domain.Employee;
import echorich.hr.employees.domain.EmployeeSalaryHistory;
import echorich.hr.employees.repository.EmployeeSalaryHistoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeSalaryHistoryService {

	private final EmployeeSalaryHistoryRepository employeeSalaryHistoryRepository;

	@Transactional
	public void increaseEmployeesSalary(List<Employee> employees, BigDecimal increasePct) {
		List<EmployeeSalaryHistory> employeeSalaryHistories = new ArrayList<>();

		employees.forEach(
			employee -> {
				BigDecimal nowSalary = employee.getSalary();
				BigDecimal increaseSalary = employee.getSalary().multiply(increasePct);
				employee.updateSalary(nowSalary.add(increaseSalary));

				employeeSalaryHistories.add(
					EmployeeSalaryHistory.of(nowSalary, employee)
				);
			}
		);

		save(employeeSalaryHistories);
	}

	@Transactional
	public void save(List<EmployeeSalaryHistory> employeeSalaryHistories) {
		employeeSalaryHistoryRepository.saveAll(employeeSalaryHistories);
	}
}
