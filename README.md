### Rail Road

## Створити проєкт, який реалізує запропоноване завдання. Відобразити заплановану діаграму взаємозв’язків між класами. При проєктуванні використати паттерни з обґрунтуванням їх доцільності. Передбачити опрацювання типових виняткових ситуацій.  Вибір технології для візуалізації програми відбувається студентами. При проєктуванні системи врахувати, що вона може еволюціонувати, а супровід програмного забезпечення має бути відбуватися з якомога меншими затратами. Зокрема, слід передбачити, що в подальшому можлива зміна візуального відображення системи. Захист командної роботи передбачає створення презентації з зазначенням прізвищ учасників та зазначенням частини виконаної роботи, а також демонстрації роботи програми, обґрунтування вибраної моделі проєктування та  аналізу застосованих технологій

1. Можливість конфігурувати систему, визначаючи кількість кас та координати їх розташувань в приміщенні, визначати кількість входів в приміщення, в яких можуть з’являтись потенційні клієнти, визначати в певному діапазоні норму часу, який буде витрачатись на обслуговування одного квитка.

2. Програма повинна автоматично генерувати клієнтів за різними стратегіями щодо часу появи(через рівномірні проміжки часу, через випадкові проміжки часу і т. д. ). Клієнт може мати особливий статус (інвалід, клієнт з дитиною, інша пільгова категорія). Наявність статусу визначає пріоритет обслуговування в касі.

3. Створені клієнти повинні мати намір купити задану генератором випадкову кількість квитків. 

4. Клієнт має можливість обирати одну з кас за принципом вибору тієї, в черзі до якої є найменша кількість людей. Якщо кількість людей в черзі  є однаковою, то клієнт обирає ту, яка є найближчою.

5. Одна з кас є резервною, тобто такою, яка обслуговує клієнтів при технічній несправності штатних кас. У випадку закриття каси на технічну перерву, клієнти з черги до цієї каси повинні бути переміщені до резервної каси зі збереженням порядку слідування.

6. У випадку, якщо сумарна кількість людей в приміщенні, перевищує допустиму норму, вокзал обмежує доступ до приміщення, генератор клієнтів повинен припинити генерацію до тих пір, поки кількість людей не стане меншою за 70% за норму.

7. У випадку, коли клієнта обслужили в касі, вважати, що він негайно покидає приміщення.

8. Візуалізація системи передбачає відображення переміщення та  процес формування можливих черг та переміщення осіб в межах черги. Процес обслуговування клієнта повинен бути зафіксований в журналі логування з зазначеним номером каси, яка обслуговувала клієнта, унікальним номером клієнта та стартовим і кінцевим часом обслуговування клієнта біля каси.
