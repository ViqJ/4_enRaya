# 4_enRaya
Juego básico del clasico 4 en raya, con la posibilidad de ampliar la cantidad de jugadores, tablero y minimo para ganar

Notas Profesor

TAREA PROGRAMADA 1 (Valor 10%)

Se permite máximo en parejas, pero se recomienda hacerlo individual.

Elegir alguno de los siguientes 3 juegos:

"Conecta 4"
"2048"
"Buscaminas"


Cree una clase que administre el juego.

Como puede notarlo, deberá llevar el control del estado del juego por medio de una matriz de las dimensiones del tablero respectivo.
La matriz debe ser privada, es decir, solamente la clase que contiene la matriz la podrá administrar.
Cree los métodos públicos para que desde "el exterior" alguien pueda utilizar su clase, su juego.
En esta clase no debe haber ningún System.out.println() ni tampoco ningún Scanner. Esta clase sólo administra el juego, consta de los métodos que validan jugadas y demás.
Esta clase debe poder ser instanciada, esto sería útil para que puedan haber más de una partida del juego jugándose a la vez.


Cree una clase que administre la interacción con el usuario (GUI).

Esta clase debe contener los métodos para mostrar el menú al usuario y la jugabilidad y demás.
Debe tener un método que imprima la ayuda al usuario. Este método se ejecutará si su programa recibe desde la línea de comandos el parámetro "-h" o "-help", por ejemplo, si su programa se llama Juego, entonces si lo ejecutamos con el comando "java Juego -h" o "java Juego -help" entonces su programa imprime la ayuda antes de mostrarle el menú de juego.
Esta clase consume a la clase que administra el juego. Muestra un menú con las acciones que el usuario debe ejecutar para poder jugar. También se encarga de mostrar el tablero de juego.
Aquí sí debe usar System.out.println y Scanner para que el usuario juegue.
Esta clase debe tener sus miembros estáticos.


Finalmente, cree una clase por aparte de las otras 2 que contenga el método principal y que este inicie el resto del programa.

El código fuente debe estar documentado con los comentarios "javadoc" que hemos mencionado en clase. Esto incluye la sección de autores.

El código fuente debe ser entregado sin errores de compilación al menos, ni tampoco de ejecución.

Debe escribir el código siguiendo las buenas prácticas básicas:

Nombres significativos en las variables
Ahorro de bloques if-else por medio de ciclos en lo posible
Hacer métodos puntuales que reciban parámetros y devuelvan resultados
No utilizar números mágicos a lo largo del código en lugar de usar variables
No usar variables aisladas que podrían usarse en un arreglo de mejor forma
Etc.


Se prestará atención que el código que presenten se mantenga dentro del margen propio de lo visto en el el curso. Cualquier consulta sobre algo que requieran utilizar, por ejemplo una biblioteca que no sea de las estándares (Math para random por ejemplo), deberán consultarlo antes.



El archivo final (sea cual sea el IDE que use) debe contener las 3 clases anteriores y debe venir comprimido en un archivo (.ZIP, .RAR) con el siguiente formato:



TP1_Progra1_Carnet.zip

TP1_Progra1_Carnet1_Carnet2.zip



El enlace de entrega pronto se estará publicando. ¡Muchos éxitos!