/*
 * package com.example.demo;
 * 
 * import java.text.SimpleDateFormat; import java.util.Calendar;
 * 
 * public class SqlTime { public static void main(String[] args) {
 * 
 * // long now = System.currentTimeMillis(); // Date sqlDate = new Date(now); //
 * System.out.println("SqlDate          : " + sqlDate); //
 * System.out.println("SqlDate.getTime(): " + sqlDate.getTime()); //
 * System.out.println("Month is : " + sqlDate.getMonth()); //
 * System.out.println(LocalDateTime.now().getHour());
 * 
 * // Calendar cal = Calendar.getInstance(); //
 * System.out.println(cal.getTime()); // cal.add(Calendar.MONTH, 1); //
 * System.out.println(cal.getTime());
 * 
 * String[] months = { "January", "February", "March", "April", "May", "June",
 * "July", "August", "September", "October", "November", "December", "January",
 * "February", "March", "April", "May", "June", "July", "August", "September",
 * "October", "November", "December", "January", "February", "March", "April",
 * "May", "June", "July", "August", "September", "October", "November",
 * "December", "January", "February", "March", "April", "May", "June", "July",
 * "August", "September", "October", "November", "December", "January",
 * "February", "March", "April", "May", "June", "July", "August", "September",
 * "October", "November", "December", "January", "February", "March", "April",
 * "May", "June", "July", "August", "September", "October", "November",
 * "December", "January", "February", "March", "April", "May", "June", "July",
 * "August", "September", "October", "November", "December", "January",
 * "February", "March", "April", "May", "June", "July", "August", "September",
 * "October", "November", "December", "January", "February", "March", "April",
 * "May", "June", "July", "August", "September", "October", "November",
 * "December" }; Calendar cal = Calendar.getInstance(); String m = new
 * SimpleDateFormat("MM").format(cal.getTime()); String y = new
 * SimpleDateFormat("yyyy").format(cal.getTime()); int month =
 * Integer.parseInt(m) - 1; int year = Integer.parseInt(y); int payMonth = 52;
 * System.out.println(y);
 * 
 * for (int i = month; i < month + payMonth; i++) { if (months[i] == "January")
 * { year += 1; } System.out.println(months[i] + ", " + year); } } }
 */