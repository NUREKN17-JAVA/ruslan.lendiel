package ua.nure.cs.lendiel.usermanagement;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    // Тест, проверяющий метод User.GetFullName
    
    // Тесты актуальны на 14.10.2019
    
    public static final String USER_FIRST_NAME = "Ruslan";
    public static final String USER_LAST_NAME = "Lendiel";
    public static final String ETALON_USER_FULL_NAME = "Lendiel, Ruslan";
    
    // Тест #1
    // Заданный день является днем рождения
    public static final int YEAR_OF_BIRTH_1 = 1999;
    public static final int MONTH_OF_BIRTH_1 = Calendar.MAY;
    public static final int DAY_OF_BIRTH_1 = 14;
    public static final int ETALON_AGE_1 = 20;
    
    // Тест #2
    // День рождения будет в данном месяце (но ещё не наступил)
    // Данный тест демонстрирует ошибочность предположения, что
    // Возраст = Текущий год - Год рождения
    public static final int YEAR_OF_BIRTH_2 = 1999;
    public static final int MONTH_OF_BIRTH_2 = Calendar.OCTOBER;
    public static final int DAY_OF_BIRTH_2 = 16;
    public static final int ETALON_AGE_2 = 19;
    
    // Тест #3
    // День рождения был в данном месяце (уже прошел)
    public static final int YEAR_OF_BIRTH_3 = 1999;
    public static final int MONTH_OF_BIRTH_3 = Calendar.OCTOBER;
    public static final int DAY_OF_BIRTH_3 = 3;
    public static final int ETALON_AGE_3 = 20;
    
    // Тест #4
    // День рождения будет больше, чем через месяц
    public static final int YEAR_OF_BIRTH_4 = 1999;
    public static final int MONTH_OF_BIRTH_4 = Calendar.DECEMBER;
    public static final int DAY_OF_BIRTH_4 = 24;
    public static final int ETALON_AGE_4 = 19;
    
    // Тест #5
    // После дня рождения прошло больше месяца
    public static final int YEAR_OF_BIRTH_5 = 1999;
    public static final int MONTH_OF_BIRTH_5 = Calendar.JULY;
    public static final int DAY_OF_BIRTH_5 = 12;
    public static final int ETALON_AGE_5 = 20;
    
    private User user;
    private Date dateOfBirth;
    
    protected void setUp() throws Exception {
        user = new User();
    }

    public void testGetFullName() {
        user.setFirstName(USER_FIRST_NAME);
        user.setLastName(USER_LAST_NAME);
        assertEquals(ETALON_USER_FULL_NAME, user.getFullName());
    }
    
    public void testGetAge1() {
       Calendar calendar = Calendar.getInstance();
       calendar.set(YEAR_OF_BIRTH_1, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
       dateOfBirth = calendar.getTime();
       user.setDateOfBirth(dateOfBirth);
       assertEquals(ETALON_AGE_1, user.getAge());
    }
    
    public void testGetAge2() {
       Calendar calendar = Calendar.getInstance();
       calendar.set(YEAR_OF_BIRTH_2, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
       dateOfBirth = calendar.getTime();
       user.setDateOfBirth(dateOfBirth);
       assertEquals(ETALON_AGE_2, user.getAge());
    }
    
    public void testGetAge3() {
       Calendar calendar = Calendar.getInstance();
       calendar.set(YEAR_OF_BIRTH_3, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);
       dateOfBirth = calendar.getTime();
       user.setDateOfBirth(dateOfBirth);
       assertEquals(ETALON_AGE_3, user.getAge());
    }
    
    public void testGetAge4() {
       Calendar calendar = Calendar.getInstance();
       calendar.set(YEAR_OF_BIRTH_4, MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);
       dateOfBirth = calendar.getTime();
       user.setDateOfBirth(dateOfBirth);
       assertEquals(ETALON_AGE_4, user.getAge());
    }
    
    public void testGetAge5() {
       Calendar calendar = Calendar.getInstance();
       calendar.set(YEAR_OF_BIRTH_5, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);
       dateOfBirth = calendar.getTime();
       user.setDateOfBirth(dateOfBirth);
       assertEquals(ETALON_AGE_5, user.getAge());
    }
}
