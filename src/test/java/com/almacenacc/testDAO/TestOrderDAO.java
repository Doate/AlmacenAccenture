package com.almacenacc.testDAO;

import java.util.Date;

import org.springframework.stereotype.Component;
import junit.framework.TestCase;

import com.almacenacc.DAO.BillDAO;
import com.almacenacc.DAO.OrderDAO;
import com.almacenacc.DAO.ProductDAO;
import com.almacenacc.model.Bill;
import com.almacenacc.model.Order;


public class TestOrderDAO extends TestCase {
	private OrderDAO orderDAOTest;
	private BillDAO billDAOTest;
	private ProductDAO productDAOTest;
	private static Integer orderCounter=3;
}
