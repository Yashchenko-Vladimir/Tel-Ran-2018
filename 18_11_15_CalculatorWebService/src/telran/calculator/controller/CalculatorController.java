package telran.calculator.controller;

import static telran.calculator.dto.CalculatorApi.*;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.calculator.dto.*;
import telran.calculator.service.ICalculator;

//@RestController
//public class CalculatorController {
//	@Autowired
//	ICalculator calculator;
//	@GetMapping(value = NUMBER_PRESENTATION +"/{"+ NUMBER + "}")
//	public ResponseCalculator getNumberPresentation
//	(@PathVariable(NUMBER) int number, @RequestParam(BASE) int base) {
//		try {
//			String result = calculator.numberPresintation(number, base);
//			ResponsePresentation response = new ResponsePresentation();
//			response.status = SUCCESS;
//			response.result = result;
//			return response;
//		} catch (Exception e) {
//			ResponseCalculator errorMessage = new ResponseCalculator();
//			errorMessage.status = ERROR;
//			errorMessage.message = e.getMessage();
//			return errorMessage;
//		}
//	}
//	
//	@GetMapping(value = OP)
//	public String getOp(){
//		return "All work";
//	}
//	
//	
//	@GetMapping(value = OPERATIONS)
//	public Set<String> getOperations(){
//		return calculator.getSupportedOperations();
//	}
//	
//	@PostMapping(value = CALCULATE)
//	public ResponseCalculator calculate(CalculateData data) {
//		try {
//			int result = calculator.calculate(data.op1, data.op2, data.operation);
//			ResponseCalculate response = new ResponseCalculate();
//			response.status = SUCCESS;
//			response.result = result;
//			return response;
//		} catch (Exception e) {
//			ResponseCalculator errorMessage = new ResponseCalculator();
//			errorMessage.status = ERROR;
//			errorMessage.message = e.getMessage();
//			return errorMessage;
//		}
//	}
//
//}
@RestController
public class CalculatorController {
@Autowired
	ICalculator calculator;
@GetMapping(value=NUMBER_PRESENTATION+"/{"+NUMBER+"}")
public ResponseCalculator getNumberPresentation
(@PathVariable(NUMBER) int number,
		@RequestParam(BASE) int base) {
	try {
		String result=calculator.numberPresentation
				(number, base);
		ResponsePresentation response=new ResponsePresentation();
		response.status=SUCCESS;
		response.result=result;
		return response;
	}catch(Exception e) {
		ResponseCalculator errorMessage=new ResponseCalculator();
		errorMessage.status=ERROR;
		errorMessage.message=e.getMessage();
		return errorMessage;
	}
}
@GetMapping(value=OPERATIONS)
public Set<String> getOperations(){
	return calculator.getSupportedOperations();
}
@PostMapping(value=CALCULATE)
public ResponseCalculator calculate(@RequestBody CalculateData data) {
	try {
		int result=calculator.calculate(data.op1, data.op2,
				data.operation);
		ResponseCalculate response=new ResponseCalculate();
		response.status=SUCCESS;
		response.result=result;
		return response;
	} catch (Exception e) {
		ResponseCalculator errorMessage=new ResponseCalculator();
		errorMessage.status=ERROR;
		errorMessage.message=e.getMessage();
		return errorMessage;
	}
}
}
