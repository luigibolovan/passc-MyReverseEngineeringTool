# Tema 2 - Reflection
### Enunt

Utilizand capacitatile reflective din java (java.lang.reflection) sau din .NET (System.Reflection) sa se implementeze o varianta de program simplu de reverse engineering care extrage informatii din cod compilat.

Programul accepta ca argument in linia de comanda numele unui fisier compilat (*.jar pentru java sau *.exe/*.dll pentru .NET) si extrage toate informatiile necesare trasarii unei diagrame de clase pentru proiectul continut. Se precizeaza ca nu este necesar a se face reprezentarea grafica a diagramei de clase, doar a se extrage toate informatiile necesare construirii ei (acestea pot fi afisate intr-un format textual oarecare). Pentru fiecare clasa/interfata se va afisa:
 - numele tipului, daca este clasa sau interfata, daca este publica, daca este abstracta
 - numele interfetelor implementate si al clasei extinse
 - lista campurilor clasei; pentru fiecare camp se va afisa: numele campului, daca este public/ privat, tipul campului
 - lista constructorilor si metodelor; pentru fiecare metoda, se vor afisa: numele metodei, numele tipurilor argumentelor, numele tipului returnat.
 
 **Link** - **_http://staff.cs.upt.ro/~ioana/arhit/2017/t2a1.html_**
