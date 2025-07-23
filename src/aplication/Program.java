package aplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("==== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(4);
		System.out.println(seller);

		System.out.println("\n==== TEST 2: seller findByDepartment ====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n==== TEST 3: seller finAll ====");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n==== TEST 4: seller insert ====");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date utilDate = sdf.parse("22/07/2025");
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			Seller newSeller = new Seller(null, "greg", "greg@gmail.com", sqlDate, 4000.0, department);
			sellerDao.insert(newSeller);
			System.out.println("Inserted! New id = " + newSeller.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n==== TEST 5: seller insert ====");
		seller = sellerDao.findById(1);
		seller.setName("Marta Waine");
		sellerDao.update(seller);
		System.out.println("Update completed");

		System.out.println("\n==== TEST 6: seller delete ====");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();

	}

}
