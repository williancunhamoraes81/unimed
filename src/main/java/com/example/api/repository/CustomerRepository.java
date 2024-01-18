package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Page<Customer> findAllByOrderByNameAsc(Pageable pageable);

	@Query("SELECT c FROM Customer c " +
			" LEFT JOIN Address a on a.customer = c.id " +
			" WHERE "
			+ "(:name IS NULL OR UPPER(c.name) LIKE UPPER(concat('%', :name, '%'))) AND "
			+ "(:email IS NULL OR UPPER(c.email) LIKE UPPER(concat('%', :email, '%'))) AND "
			+ "(:gender IS NULL OR UPPER(c.gender) = UPPER(:gender)) AND "
			+ "(:city IS NULL OR UPPER(a.city) LIKE UPPER(concat('%', :city, '%')))")
	Page<Customer> findByFilters(
			@Param("name") String name,
			@Param("email") String email,
			@Param("gender") String gender,
			@Param("city") String city
			, Pageable pageable);

}
