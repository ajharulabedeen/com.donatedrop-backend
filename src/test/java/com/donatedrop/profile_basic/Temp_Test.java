/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.profile_basic;

import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author G7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Temp_Test {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    Dao_Profile_Basic_I dao_Profile_Basic_I;

    public Temp_Test() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test1_readAddress() {
//        System.out.println("entityManager : " + entityManager.toString());
//        Address address = entityManager.find(Address.class, new Long(29));
//        entityManager.remove(address);
//        dao_Profile_Basic_I.updateAddress("29");

//        System.out.println("\n\n" + address.toString() + "\n\n");

    }

}
/**
 * Old temp Test codes
 *
 * @author G7
 * <p>
 * // TODO add test methods here. // The methods must be annotated with
 * annotation @Test. For example: //
 * @Test
 * @Order(2) public void m2() {
 * <p>
 * System.out.println("M1"); assertEquals("OK","OK"); assertEquals("OK1","OK1");
 * assertEquals("OKL","OKL"); }
 * @Test
 * @Order(1) public void zm1() { System.out.println("M2"); }
 * <p>
 * // @Test public void m1_1Write() { Charset utf8 = StandardCharsets.UTF_8;
 * List<String> list = Arrays.asList("Line 1"); try { // If the file doesn't
 * exists, create and write to it // If the file exists, truncate (remove all
 * content) and write to it Files.write(Paths.get("log/profile_basic.log"),
 * list, utf8); } catch (IOException x) { System.err.format("IOException: %s%n",
 * x); } }
 * @Test public void m1_Read() { // Read try { byte[] content =
 * Files.readAllBytes(Paths.get("log/profile_basic.log")); String s = new
 * String(content); String[] x = s.split(" "); System.out.println("x : " +
 * x.length); System.out.println(">" + s + "<");
 * System.out.println(">" + x[0] + "<");
 * System.out.println(">" + x[1] + "<"); } catch (IOException e) {
 * e.printStackTrace(); }
 * <p>
 * }
 * <p>
 * // TODO add test methods here. // The methods must be annotated with
 * annotation @Test. For example: //
 * @Test
 * @Order(2) public void m2() {
 * <p>
 * System.out.println("M1"); assertEquals("OK","OK"); assertEquals("OK1","OK1");
 * assertEquals("OKL","OKL"); }
 * @Test
 * @Order(1) public void zm1() { System.out.println("M2"); }
 * <p>
 * // @Test public void m1_1Write() { Charset utf8 = StandardCharsets.UTF_8;
 * List<String> list = Arrays.asList("Line 1"); try { // If the file doesn't
 * exists, create and write to it // If the file exists, truncate (remove all
 * content) and write to it Files.write(Paths.get("log/profile_basic.log"),
 * list, utf8); } catch (IOException x) { System.err.format("IOException: %s%n",
 * x); } }
 * @Test public void m1_Read() { // Read try { byte[] content =
 * Files.readAllBytes(Paths.get("log/profile_basic.log")); String s = new
 * String(content); String[] x = s.split(" "); System.out.println("x : " +
 * x.length); System.out.println(">" + s + "<");
 * System.out.println(">" + x[0] + "<");
 * System.out.println(">" + x[1] + "<"); } catch (IOException e) {
 * e.printStackTrace(); }
 * <p>
 * }
 * <p>
 * // TODO add test methods here. // The methods must be annotated with
 * annotation @Test. For example: //
 * @Test
 * @Order(2) public void m2() {
 * <p>
 * System.out.println("M1"); assertEquals("OK","OK"); assertEquals("OK1","OK1");
 * assertEquals("OKL","OKL"); }
 * @Test
 * @Order(1) public void zm1() { System.out.println("M2"); }
 * <p>
 * // @Test public void m1_1Write() { Charset utf8 = StandardCharsets.UTF_8;
 * List<String> list = Arrays.asList("Line 1"); try { // If the file doesn't
 * exists, create and write to it // If the file exists, truncate (remove all
 * content) and write to it Files.write(Paths.get("log/profile_basic.log"),
 * list, utf8); } catch (IOException x) { System.err.format("IOException: %s%n",
 * x); } }
 * @Test public void m1_Read() { // Read try { byte[] content =
 * Files.readAllBytes(Paths.get("log/profile_basic.log")); String s = new
 * String(content); String[] x = s.split(" "); System.out.println("x : " +
 * x.length); System.out.println(">" + s + "<");
 * System.out.println(">" + x[0] + "<");
 * System.out.println(">" + x[1] + "<"); } catch (IOException e) {
 * e.printStackTrace(); }
 * <p>
 * }
 * <p>
 * // TODO add test methods here. // The methods must be annotated with
 * annotation @Test. For example: //
 * @Test
 * @Order(2) public void m2() {
 * <p>
 * System.out.println("M1"); assertEquals("OK","OK"); assertEquals("OK1","OK1");
 * assertEquals("OKL","OKL"); }
 * @Test
 * @Order(1) public void zm1() { System.out.println("M2"); }
 * <p>
 * // @Test public void m1_1Write() { Charset utf8 = StandardCharsets.UTF_8;
 * List<String> list = Arrays.asList("Line 1"); try { // If the file doesn't
 * exists, create and write to it // If the file exists, truncate (remove all
 * content) and write to it Files.write(Paths.get("log/profile_basic.log"),
 * list, utf8); } catch (IOException x) { System.err.format("IOException: %s%n",
 * x); } }
 * @Test public void m1_Read() { // Read try { byte[] content =
 * Files.readAllBytes(Paths.get("log/profile_basic.log")); String s = new
 * String(content); String[] x = s.split(" "); System.out.println("x : " +
 * x.length); System.out.println(">" + s + "<");
 * System.out.println(">" + x[0] + "<");
 * System.out.println(">" + x[1] + "<"); } catch (IOException e) {
 * e.printStackTrace(); }
 * <p>
 * }
 * <p>
 * // TODO add test methods here. // The methods must be annotated with
 * annotation @Test. For example: //
 * @Test
 * @Order(2) public void m2() {
 * <p>
 * System.out.println("M1"); assertEquals("OK","OK"); assertEquals("OK1","OK1");
 * assertEquals("OKL","OKL"); }
 * @Test
 * @Order(1) public void zm1() { System.out.println("M2"); }
 * <p>
 * // @Test public void m1_1Write() { Charset utf8 = StandardCharsets.UTF_8;
 * List<String> list = Arrays.asList("Line 1"); try { // If the file doesn't
 * exists, create and write to it // If the file exists, truncate (remove all
 * content) and write to it Files.write(Paths.get("log/profile_basic.log"),
 * list, utf8); } catch (IOException x) { System.err.format("IOException: %s%n",
 * x); } }
 * @Test public void m1_Read() { // Read try { byte[] content =
 * Files.readAllBytes(Paths.get("log/profile_basic.log")); String s = new
 * String(content); String[] x = s.split(" "); System.out.println("x : " +
 * x.length); System.out.println(">" + s + "<");
 * System.out.println(">" + x[0] + "<");
 * System.out.println(">" + x[1] + "<"); } catch (IOException e) {
 * e.printStackTrace(); }
 * <p>
 * }
 */

//Method oderig ang read PK from .log file.
/**
 *    // TODO add test methods here. // The methods must be annotated with
 * annotation @Test. For example: //
 *
 * @Test
 * @Order(2) public void m2() {
 *
 * System.out.println("M1"); assertEquals("OK","OK"); assertEquals("OK1","OK1");
 * assertEquals("OKL","OKL"); }
 *
 * @Test
 * @Order(1) public void zm1() { System.out.println("M2"); }
 *
 * // @Test public void m1_1Write() { Charset utf8 = StandardCharsets.UTF_8;
 * List<String> list = Arrays.asList("Line 1"); try { // If the file doesn't
 * exists, create and write to it // If the file exists, truncate (remove all
 * content) and write to it Files.write(Paths.get("log/profile_basic.log"),
 * list, utf8); } catch (IOException x) { System.err.format("IOException: %s%n",
 * x); } }
 *
 * @Test public void m1_Read() { // Read try { byte[] content =
 * Files.readAllBytes(Paths.get("log/profile_basic.log")); String s = new
 * String(content); String[] x = s.split(" "); System.out.println("x : " +
 * x.length); System.out.println(">" + s + "<");
 * System.out.println(">" + x[0] + "<");
 * System.out.println(">" + x[1] + "<"); } catch (IOException e) {
 * e.printStackTrace(); }
 *
 * }
 */
