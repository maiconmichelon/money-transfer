package com.michelon.application;

import java.util.concurrent.Callable;

import com.google.gson.Gson;
import com.michelon.controllers.AccountController;
import com.michelon.controllers.BalanceController;
import com.michelon.controllers.Controller;
import com.michelon.controllers.PostController;
import com.michelon.controllers.TransactionController;
import com.michelon.dto.response.ErrorDto;
import com.michelon.dto.response.ResponseDto;

import spark.Response;
import spark.Spark;

public class Application {

    public static void main(String[] args) throws Exception {
        Application application = new Application();
		Spark.path("/api", () -> application.mapApiRoutes());
    }
    
    private void mapApiRoutes() {
        BalanceController balanceController = new BalanceController(Configuration.getAccountService());
        mapApiRoute(balanceController);

        TransactionController transactionController = new TransactionController(Configuration.getTransactionService());
        mapApiRoute(transactionController);
        
        AccountController accountController = new AccountController(Configuration.getAccountService());
        mapApiRoute(accountController);
    }

	private <T> void mapApiRoute(PostController<T> controller) {
		Spark.post(controller.getRoute(), ((request, response) -> {
            T requestDto = new Gson().fromJson(request.body(), controller.getBodyClass());
            ResponseDto<?> handle = safeExecute(() -> controller.handle(request, requestDto));
			return buildResponse(handle, response);
        }));
	}

	private void mapApiRoute(Controller controller) {
		Spark.get(controller.getRoute(), ((request, response) -> {
        	ResponseDto<?> handle = safeExecute(() -> controller.handle(request));
        	return buildResponse(handle, response);
        }));
	}

	private ResponseDto<?> safeExecute(Callable<ResponseDto<?>> callable) throws Exception {
		try {
			return callable.call();
		} catch(IllegalArgumentException ex) {
			return new ResponseDto<>(400, new ErrorDto(ex.getMessage()));
		}
	}

	private Object buildResponse(ResponseDto<?> responseDto, Response response) {
		response.header("Content-Type", "application/json");
		response.status(responseDto.getStatusCode());
		return new Gson().toJson(responseDto);
	}
	
}
