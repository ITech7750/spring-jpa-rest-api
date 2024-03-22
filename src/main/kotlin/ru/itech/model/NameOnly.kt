package ru.itech.model

/* Создаем проекцию, чтобы уменьшить кол-во данных,
подтягиваемых из БД. Для функции findAllByOrderByTitle() используем
List<TitleOnly> вместо  List<CommunityEntity>,
те считываем только title*/
class NameOnly (
    var name: String
)