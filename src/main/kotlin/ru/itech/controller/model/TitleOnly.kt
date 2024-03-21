package ru.itech.controller.model

/* Создаем проекцию, чтобы уменьшить кол-во данных,
подтягиваемых из БД. Для функции findAllByOrderByTitle() используем
List<TitleOnly> вместо  List<CommunityEntity>,
те считываем только title*/
class TitleOnly (
    var title: String
)