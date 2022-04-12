# Hero_picker

Данный проект задумывался как помощник к игре Dota2 в выборе героя основываясь на статистике, а так же возможность просматривать статистику. 

Реализован вход через Steam API , что позволяет получить доступ к базе Steam. Ответ от базы донных поступает в JSON. Реализован парсинг JSON. 

После входа открывается доступ к персонажам. Раздел профиль не реализован , поэтому закрыть. Посмотреть данный раздел можно изменив в классе Login 
строку Parent profile = FXMLLoader.load(getClass().getResource("../pick.fxml")); на Parent profile = FXMLLoader.load(getClass().getResource("../profile/profile.fxml"));

Java 8, JavaFX , CSS
