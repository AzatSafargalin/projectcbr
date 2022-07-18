# projectcbr
Требования для запуска проекта:
 - Tomcat v.8.5.75
 - PostgreSQL v 14.4
 - JDK/JRE 1.8.0_311
 - Maven 3.8.5


Для запуска проекта выполнить следующие действия:
  1. Создать пользователя и схему для проекта project-cbr, выдать все необходимые права пользователю
  2. Поправить строку подключения в project-model/src/main/resources/META-INF/persistence.xml (Выставить логин и пароль нового пользователя)
  3. В application.properties так же выставить данные подключения, указать схему
  4. Собрать проект, перенести war файл в папку ${TOMCAT_HOME}/webapps, заменить context.xml запустить томкат, либо можно запускать из ide

Endpoints:
  1. saveXsd:
    POST /project-web/save/xmlXsdBound

          <XmlXsdPair>
            <xmlName>user.xml</xmlName>
            <xsdName>user.xsd</xsdName>
          </XmlXsdPair>

  2. validateXml:
    POST /project-web/validate

          <XmlEntity>
            <xmlName>user.xml</xmlName>
          </XmlEntity>

  3. saveXml:
    POST /project-web/save/xml

          <XmlEntity>
            <xmlName>user.xml</xmlName>
          </XmlEntity>

  4. getXml:
    GET /project-web/download/getFileLink?fileName=user.xml Получение информации о файле + ссылка на файл
    GET /project-web/download/getFile?fileName=user.xml Получение файла
  
TODOS:
  1. Проверить валидацию (@Valid, @NotBlanc, @NotNull, etc...)
  2. Перепроверить/переделать @Transactional
  3. Написать Юнит-Тесты
  4. Переделать GenericExceptionMapper
  5. Настроить логгирование (logback.xml)
  6. Настроить профили для тестирования/запуска приложения
