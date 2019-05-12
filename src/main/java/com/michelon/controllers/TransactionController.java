package com.michelon.controllers;

import static spark.utils.StringUtils.isEmpty;

import java.math.BigDecimal;

import com.michelon.dto.request.CreateTransactionRequestDto;
import com.michelon.dto.response.ErrorDto;
import com.michelon.dto.response.ResponseDto;
import com.michelon.exception.AccountNotFoundException;
import com.michelon.service.TransactionService;

import spark.Request;

public class TransactionController implements PostController<CreateTransactionRequestDto> {

    public static final String ROUTE = "/transaction";
	private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ResponseDto<?> handle(Request request, CreateTransactionRequestDto createTransactionRequestDto) {
        try {
        	return createTransaction(createTransactionRequestDto);
        } catch(AccountNotFoundException ex) {
            return new ResponseDto<>(400, new ErrorDto(ex.getMessage()));
        }
    }

	private ResponseDto<?> createTransaction(CreateTransactionRequestDto createTransactionRequestDto) throws AccountNotFoundException {
		validate(createTransactionRequestDto);
		transactionService.transfer(createTransactionRequestDto);
		return new ResponseDto<>(200);
	}

	private void validate(CreateTransactionRequestDto createTransactionRequestDto) {
		if (isEmpty(createTransactionRequestDto.getSourceAccountId())) 
			throw new IllegalArgumentException("souceAccountId must be not null.");
		if (isEmpty(createTransactionRequestDto.getTargetAccountId()))
			throw new IllegalArgumentException("souceAccountId must be not null.");
		BigDecimal value = createTransactionRequestDto.getValue();
		if (value == null || value.signum() <= 0)
			throw new IllegalArgumentException("value has an invalid value.");
	}

	@Override
	public Class<CreateTransactionRequestDto> getBodyClass() {
		return CreateTransactionRequestDto.class;
	}

	@Override
	public String getRoute() {
		return ROUTE;
	}
}
