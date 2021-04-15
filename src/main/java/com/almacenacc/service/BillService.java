package com.almacenacc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.almacenacc.DAO.BillDAO;
import com.almacenacc.model.Bill;

@RestController
public class BillService {
	@Autowired
	private BillDAO billService;
	

	@GetMapping("/facturas")
	public List<Bill> getAllBills()
	{
		return billService.getAllBills();
	}
	

	@GetMapping("/facturas/{idFactura}")
	public Bill getFacturaById(@PathVariable int idFactura)
	{
		return billService.getBillbyId(idFactura);
	}
	

}
