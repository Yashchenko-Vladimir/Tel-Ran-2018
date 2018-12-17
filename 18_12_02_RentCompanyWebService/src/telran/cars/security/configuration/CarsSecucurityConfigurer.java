package telran.cars.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.cars.dto.*;

import telran.security.accounting.Authenticator;
import telran.security.accounting.service.AccountingMongo;

@Configuration
@EnableMongoRepositories("telran.security.accounting.repository")
public class CarsSecucurityConfigurer extends WebSecurityConfigurerAdapter{
	@Bean
	Authenticator getAuthenticator() {
		return new Authenticator();
	}
	
	@Bean
	AccountingMongo getAccountingMongo() {
		return new AccountingMongo();
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic();
		http.csrf();
		http.authorizeRequests().antMatchers(RentCompanyConstants.ADD_CAR, RentCompanyConstants.ADD_MODEL,
				RentCompanyConstants.REMOVE_CAR, RentCompanyConstants.CLEAR).hasAnyRole(RentCompanyRoles.MANAGER).
		antMatchers(RentCompanyConstants.ADD_DRIVER, RentCompanyConstants.RENT_CAR,
				RentCompanyConstants.RETURN_CAR, RentCompanyConstants.GET_CAR, RentCompanyConstants.GET_DRIVER).
				hasAnyRole(RentCompanyRoles.CLERK).
		antMatchers(RentCompanyConstants.GET_CAR_DRIVERS, RentCompanyConstants.GET_DRIVER_CARS,
				RentCompanyConstants.GET_ALL_DRIVERS, RentCompanyConstants.GET_DRIVER).hasAnyRole(RentCompanyRoles.DRIVER).
		antMatchers(RentCompanyConstants.GET_ALL_RECORDS, RentCompanyConstants.GET_RETURNED_RECORDS_BY_DATES).
				hasAnyRole(RentCompanyRoles.TECHMICIAN).
		antMatchers(RentCompanyConstants.GET_MODEL_PROFIT, RentCompanyConstants.GET_MOST_POPULAR_MODEL_NAMES,
				RentCompanyConstants.GET_MOST_PROFITABLE_MODEL_NAMES).hasAnyRole(RentCompanyRoles.STATIST);
		
		http.authorizeRequests().antMatchers(RentCompanyConstants.GET_CAR).authenticated();
		http.authorizeRequests().antMatchers("/actuator/shotdown").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().permitAll();
	}

}
