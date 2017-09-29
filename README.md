# climapp
App del clima como proyecto final

# Requerimientos:
## La app debe utilizar todos los temas dados en el curso:

* RecyclerView + CardView
* Activity + Fragments
* ActivityResults
* Layouts
* Networking
* Persistencia
  * Shared Preferences
  * Archivos Locales
  * Tabla SQLite u ORM
  
## La app debe tener un "BottomNavigation" en el cual podremos cambiar de clima del día al clima de la semana.

## Clima del día:

* Nombre de la ubicación
* Imagen del clima (Sol, nubes, lluvia, etc)
* Temperatura actual (de la última actualización)
* Temperatura minima y maxima del día
* Velocidad del Viento
* Salida / Puesta del Sol

## Clima semanal:
* Cada día debe tener las misma información del clima del día a excepcion de la temperatura actual
* Debe ser una lista de tarjetas de cada día

## Debe haber un boton para actualizar manualmente los datos.

## La app debe tener una notificación que muestre la temperatura actual y un icono con el clima del momento.

## Deben existir las siguientes configuraciones:

* Intervalo de actualizacion (Manual, 1hora, 3 horas, 6 horas, 12 horas)
* Unidad de temperatura (C/F)
* Unidad de velocidad del viento (m/s ; mph)

## EXTRAS INTERESANTES:

* Agregar un widget en la pantalla principal
* Agregar Geolocalización
* Posibilidad de agregar mas de 1 ciudad
* Animaciones
* Posibilidad de compartir el clima a través de distintas apps
* Incluir una opcion para puntuar la app en playstore

## UI
### Configuraciones
![configuraciones](https://user-images.githubusercontent.com/12887035/30678923-0aa002c8-9e6c-11e7-90e7-9dbafc153423.png)

### Pronóstico Semanal
![semana](https://user-images.githubusercontent.com/12887035/30678922-0a9fed1a-9e6c-11e7-909d-f24a4a24363e.png)

### Pronóstico del día
![dia](https://user-images.githubusercontent.com/12887035/30678924-0aa0d2f2-9e6c-11e7-98b3-c8299ee49342.png)

### Topes Nubosos
![nubes](https://user-images.githubusercontent.com/12887035/30678935-11a134d4-9e6c-11e7-827a-2f1a8e196e7f.png)

### Precipitaciones
![precipitaciones](https://user-images.githubusercontent.com/12887035/30678937-11c4c67e-9e6c-11e7-86cb-0dca942cac2b.png)

### Vientos
![vientos](https://user-images.githubusercontent.com/12887035/30678938-11e9efa8-9e6c-11e7-9e4b-81aa221b11f1.png)

## Debugging
### Base de datos y Shared Preferences
* Android Debug Database nos va a permitir ver bases de datos y preferencias compartidas directamente en su navegador de una manera muy simple.

#### ¿Qué podemos hacer con Android Debug Database?

* Ver todas las bases de datos.
* Ver todos los datos de las Shared Preferences utilizadas en la aplicación.
* Ejecutar cualquier consulta SQL en la base de datos para actualizar y eliminar datos.
* Editar directamente los valores de la base de datos.
* Editar directamente las Shared Preferences.
* Agregar directamente una fila en la base de datos.
* Agregar directamente un valor-clave en las Shared Preferences.
* Eliminar filas de base de datos y Shared Preferences.
* Buscar en los datos.
* Ordenar datos.
* Descargar la base de datos.
* Todas estas características funcionan sin necesidad de ser root

#### Configuración en el proyecto

* Agregar a build.gradle la dependencia:
```
debugCompile 'com.amitshekhar.android:debug-db:1.0.1'
```

* Eso es todo, iniciar la app en modo debug, y ver en el logcat:
```
D/DebugDB: Open http://XXX.XXX.X.XXX:8080 in your browser
```

* También podemos obtener siempre la URL de la dirección de depuración desde el código llamando al método
```
DebugDB.getAddressLog();
```

* Luego en el navegador abrimos el enlace.

#### Importante:

* El teléfono y pc deben estar conectados a la misma red (Wifi o LAN).
* Si lo estamos usando sobre usb, ejecutamos desde la terminal:
```
adb adelante tcp: 8080 tcp: 8080
```
* Si lo estamos usando con el emulador de Android ejecutamos desde la terminal:
```
adb forward tcp: 8080 tcp: 8080
``` 
  y en el navegador abrimos:
  ```
  http://localhost:8080
  ```

* Nota: Si queremos utilizar otro puerto distinto de 8080. En el archivo build.gradle de la aplicación bajo buildTypes, hacemos lo siguiente

```
debug {
    resValue ("String", "PORT_NUMBER", "8081")
}
```

