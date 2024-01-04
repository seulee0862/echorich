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
				BigDecimal increaseSalary = employee.getSalary().multiply(increasePct);
				employee.updateSalary(employee.getSalary().add(increaseSalary));

				employeeSalaryHistories.add(
					EmployeeSalaryHistory.of(employee.getSalary().subtract(increaseSalary), employee)
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
