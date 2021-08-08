- build configuration находится в папке .teamcity
- Написан новый метод для класса Welcomer - saysNew
- Дополнен тест для нового метода saysNew на поиск слова hunter

Тест пройдет успешно:


![test](https://user-images.githubusercontent.com/16622507/128642487-d9aba378-f905-4b3d-a64a-4dcfede5c45e.jpg)


Как и ожидалось, артефакт мы не собираем, если ветка не master:


![image](https://user-images.githubusercontent.com/16622507/128642502-548ed45e-6884-4a2e-8dda-e03d9b9e6471.png)


Артефакт собран успешно по ветке master:


![image](https://user-images.githubusercontent.com/16622507/128642506-5c4d96d2-e761-4284-b859-5b4d181f4df4.png)
