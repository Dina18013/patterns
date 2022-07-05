package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserGenerator {

    public static UserData generateUser(int newDay) {

        String city = generateCity();
        String date = generateDate(newDay);
        String name = generateName();
        String phone = generatePhone();
        UserData user = new UserData(city, date, name, phone);
        return user;
    }

    public static String generateCity() {

        String[] cities = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик",
                "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск",
                "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск",
                "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда",
                "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган",
                "Курск", "Гатчина", "Липецк", "Магадан", "Красногорск", "Мурманск", "Нижний Новгород",
                "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-на-Дону",
                "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск",
                "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Москва", "Санкт-Петербург", "Севастополь",
                "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};
        Faker faker = new Faker();
        int i = faker.number().numberBetween(0, cities.length - 1);
        String city = cities[i];
        return city;
    }

    static String generateDate(int newDays) {

        String meetDate = LocalDate.now().plusDays(newDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return meetDate;
    }

    public static String generateName() {

        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String generatePhone() {

        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}
