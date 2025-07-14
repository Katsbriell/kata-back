# BackEnd para la aplicaión de gestión de bases de datos

## Instalación

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

1. Clona este repositorio
2. Instalar dependencias
   ```bash
   mvn clean install

3. Correr el servidor
   ```bash
   mvn spring-boot:run

4. Se puede abrir la consola en el navegador para la bd:
   ```bash
   http://localhost:8080/h2-console

## Nota
Para el consumo a SendGrid modificar 'sendgrid.api-key' y 'sendgrid.form' en el application.properties.
Se utiliza una base de datos H2, la cual consta de dos tablas app_users y app_onboardings, esta última tiene datos predeterminos los cuales se pueden modificar con el OnboardingData.json
