# EsameGabrielliVirgili

esame di programmazione ad oggetti di Lorenzo Gabrielli e Claudio Virgili

# Introduzione

Questa applicazione ci permette di  studiare le competizioni di Calcio dell'Italia, utilizzando le API del sito football-data. 
L'applicazione permette all'utente finale di: 
1)Visualizzare le informazioni relative ad una determinata competizione italiana. 
2)Visualizzare delle statistiche generali calcolate su tutte le competizioni italiane disponibili.
3)Visualizzare delle statistiche generali calcolate su una competizione scelta e sulle sue partite. 
Inoltre, l'utente ha la possibilità di calcolare/filtrare le statistiche in base a dei filtri scelti dall'utente stesso.  

STATISTICHE
Statistiche generali: 
1)Numero minimo.
2)Massimo e medio di squadre totali.
3)Durata media delle competizioni (in giorni/mesi).
4)Quantità media di stagioni salvate per ogni competizione. 

Statistiche in base alla competizione: 
1)Quante volte l'esito del primo tempo di una partita è rimasto identico al finale.
2)Quante volte l'esito del primo tempo è stato ribaltato rispetto al secondo tempo.
3)Quante volte un'esito neutro è passato ad un esito diverso, e viceversa.
4)Numero di arbitri diversi calcolati su tutte le partite della stagione.

FILTRI
1)Filtraggio statistiche generali in base al livello delle competizioni da studiare.
2)Filtraggio delle statistiche sulle partite in base a: 
              -Una lista di squadre su cui studiare le partite; 
              -L'utilizzo di una o più stagioni disponibili; 
              -Partite selezionate rispetto ad un range temporale all'interno di una stagione.  


# Diagrammi UML
Generale





![Generale](https://user-images.githubusercontent.com/84229200/122453340-89e7ec80-cfaa-11eb-8398-7065acfa87c1.PNG)




Model






![Model](https://user-images.githubusercontent.com/84229200/122453400-9c622600-cfaa-11eb-9adf-e182b6e3cc4b.PNG)






Stats







![Stats](https://user-images.githubusercontent.com/84229200/122453444-aa17ab80-cfaa-11eb-9603-4ddefb0245b0.PNG)







Util











![Util](https://user-images.githubusercontent.com/84229200/122453480-b1d75000-cfaa-11eb-9084-b1e39c273e26.PNG)









Controller








![Controller](https://user-images.githubusercontent.com/84229200/122453552-c9163d80-cfaa-11eb-9054-e961ef5361fa.PNG)











Exception










![Exception](https://user-images.githubusercontent.com/84229200/122453505-bac82180-cfaa-11eb-8213-edb973eb4c6e.PNG)















