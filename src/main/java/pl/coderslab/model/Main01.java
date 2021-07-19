package pl.coderslab.model;

import pl.coderslab.dao.DayNameDao;

public class Main01 {
    public static void main(String[] args) {
     DayName dayName = new DayName();
     DayNameDao dayNameDao = new DayNameDao();
     dayNameDao.findAll();
    }
}
