🏰 Strategická hra (Java)

Tento projekt je jednoduchá strategická hra vytvorená v jazyku Java s dôrazom na princípy objektovo orientovaného programovania, najmä dedičnosť a polymorfizmus.

🎮 Herný koncept

Hráč spravuje mesto, v ktorom môže:

stavať nové budovy,

vylepšovať existujúce budovy,

získavať suroviny,

vytvárať jednotky a posielať ich na výpravy.

🏗️ Typy budov

Hra obsahuje viacero typov budov s rôznym účelom, napríklad:

Ťažobné budovy – slúžia na získavanie surovín.

Kasárne – umožňujú vytváranie jednotiek.

(ďalšie budovy je možné jednoducho pridať vďaka OOP návrhu)

Budovy sú implementované pomocou spoločnej abstraktnej triedy alebo rozhrania, pričom konkrétne typy budov dedia spoločné vlastnosti a implementujú vlastné správanie.

⚔️ Výpravy do jaskyne

Jednotky vytvorené v kasárňach môže hráč posielať do jaskyne.

Pri úspešnej výprave jednotky prinesú späť zdroje do mesta.

Výsledok výpravy môže závisieť od rôznych faktorov (napr. typ jednotky, náhoda, herná logika).

🧠 Technické zameranie

Projekt sa zameriava na:

návrh triednej hierarchie,

využitie dedičnosti na zdieľanie spoločného správania,

polymorfizmus pre flexibilné spracovanie rôznych typov budov a jednotiek,

rozšíriteľnosť (jednoduché pridanie nových budov alebo jednotiek).

projekt bol vytvoreny v roku 2025
