# Desarrollo de aplicaciones móviles
 ## Grupo 15:
* Rodolfo Celis
* Luis F. Navarro
* Jorge E. Castaño
* James S. Montealegre

# App Vinilos

https://user-images.githubusercontent.com/31069035/235333082-2ca690da-53a6-40ca-9caf-2e54f3da5a9b.mp4

# Ejecución y operación de la aplicación Vinilos

1. Descargar el IDE - Android Studio versión Flamingo.

Con dicha descarga también se obtiene el SDK. Previamente se aconseja descargar un JDK por ejemplo el 11

<img width="586" alt="Screenshot 2023-04-29 at 5 49 09 PM" src="https://user-images.githubusercontent.com/83414986/235331297-5838c8d8-fda5-47d5-aae4-be6202fdf681.png">


2. Clonar el proyecto del repositorio de Github.

URL: https://github.com/rcelisc/misw4203_aplicaciones_moviles.git

3. Abrir la aplicación en el entorno de desarrollo, File -> Open -> Seleccionar la ubicación en donde se clonó el repositorio

<img width="962" alt="image" src="https://user-images.githubusercontent.com/83414986/235331392-9f9bd533-04d6-4b88-8b8c-78a5b3c97623.png">

4. Contar con un dispositivo Android para ejecutar la aplicación

### Dispositivo físico:

* Primero, debes habilitar las opciones de desarrollador en tu teléfono. Para hacer esto, ve a la aplicación de "Configuración" y busca "Acerca del teléfono". En la sección "Información del software", busca el número de compilación y tócalo varias veces hasta que aparezca un mensaje que te indique que eres un desarrollador.
* Ahora que has habilitado las opciones de desarrollador, vuelve a la pantalla principal de configuración y busca "Opciones de desarrollador". Ingresando a esta opción, tendrás acceso a las funciones avanzadas de tu teléfono.
* En la sección "Opciones de depuración", asegúrate de habilitar la opción "Depuración USB".
* Conecta tu teléfono a tu computadora utilizando un cable USB.
* En Android Studio, abre el proyecto de tu aplicación y verifica que tu teléfono esté siendo reconocido por el software. Puedes hacer esto desde la barra de herramientas de Android Studio.

### Dispositivo virtual:

* Dirigite a la esquina superior derecha y busca la opción Device Manager
<img width="271" alt="image" src="https://user-images.githubusercontent.com/83414986/235331494-8d821ff3-223b-464d-931a-f59e22b60a4e.png">

* Crea un nuevo dispositivo virtual 
<img width="233" alt="image" src="https://user-images.githubusercontent.com/83414986/235331642-72cec24e-4aec-4467-acbc-d8f88eb8e773.png">

La recomendación es crear un dispositivo virtual con las siguientes especificaciones 

<img width="976" alt="image" src="https://user-images.githubusercontent.com/83414986/235331757-c96dc8aa-56eb-445e-b604-69df030a51fc.png">

5. Correr el dispositivo virtual
* Antes de ejecutar el entorno virtual se aconseja liberar espacio del dispositivo, esto es archivos no necesarios para la ejecución de la aplicación. Para esto ve a la opción Wipe Data

<img width="430" alt="image" src="https://user-images.githubusercontent.com/83414986/235331806-299ff336-3812-455c-899a-8bbc59c1ab8c.png">

* Ejecutar el entorno virtual

<img width="431" alt="image" src="https://user-images.githubusercontent.com/83414986/235331837-efdd9538-1568-4bad-92d2-82353094123a.png">

Una vez el dispositivo virtual esté encendido debe esperar hasta que esté en la pantalla de inicio para continuar con el siguiente paso.

<img width="402" alt="image" src="https://user-images.githubusercontent.com/83414986/235331894-e4f9b59c-36b1-4bb9-a17e-03fdf19dfc61.png">

6. Realice un build

Realice un build de la aplicación para garantizar que todas las dependencias han sido cargadas e inicializadas para ello:
* Dirijase al menú de navegación: Build -> Make Project
* Si se presenta algún error en el menú de navegación realice: Build -> Clean Project, Cuando esto termine realice Build -> Rebuild Project

![image](https://user-images.githubusercontent.com/83414986/235332026-580264a2-05ce-4bc0-9f81-5df65ef43dc7.png)


7. Ejecute la aplicación

<img width="380" alt="image" src="https://user-images.githubusercontent.com/83414986/235332086-5bee1e03-adc0-4627-b00a-500408829322.png">

Corra la aplicación y compruebe el funcionamiento

<img width="414" alt="image" src="https://user-images.githubusercontent.com/83414986/235332102-d5f93ae5-4f87-4ec3-a5ed-3f7bd4e616a3.png">


Recuerde que Android Studio no cuenta con un Live Reloading por lo que, si realiza cambios en el proyecto deberá detener el DVM y correr nuevamente la aplicación.

# Correr Tests y Coverage

Actualmente la covertura de clases y lineas de código estan en 68%, a continuación se presenta la manera de ejecutar los tests:

1. Identifica la carpeta del paquete que se indica con el nombre (test)
![image](https://user-images.githubusercontent.com/31069035/235333152-8cee2b9c-bc1a-40a6-be20-6e2730ad5ba3.png)

2. De click derecho sobre el paquete y posteriormente seleccione la opción "Run 'Tests in com.grupo15.vinilos' with Coverage" como se indica a continuación:
![Sin título](https://user-images.githubusercontent.com/31069035/235333271-28f4afa9-bfdd-414b-b9b1-3d23f512c6f1.png)

3. Al finalizar podrá observar el resultado de los test junto con el coverage en el packete de la aplicación:
![image](https://user-images.githubusercontent.com/31069035/235333327-8b5bf470-e738-4e0f-a29d-6a718b403004.png)

Importante prestar atención que estos valores son diferentes al resultado de la barra derecha. Concluir con estos ultimos es erroneo ya que dentro de ello, Android Studio, esta tomando archivos construidos por Hilt lo cual es invalido disminuyendo el coverage. Por ello, la conclusión se toma con el resultado del punto 3.
![image](https://user-images.githubusercontent.com/31069035/235333357-425fc446-1b3c-4d49-9e92-068b9dab05bd.png)
