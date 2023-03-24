Pro vytvoreni vlastni branche postupujte nasledovne:
1) Aktualizovat lokalni obraz repozitare:
git fetch (pripadne git pull)
1) Aktualizovat lokalni obraz repozitare: git pull
2) Zalozit novou *lokalni* branche git checkout -b username
(*username* nahradte svym CVUT loginem)
3) Vytvorit branche na serveru git push --set-upstream origin username

Pro merge main branche do vlastni branche postupujte takto:
1) Aktualizovat lokalni obraz main branche:
git checkout main a pote git fetch (pripadne git pull)
git checkout main a pote git pull
2) Prepnuti do vlastni branche:
git checkout username
3) Merge (slouceni main branche s vasi vlastni branchi):
git merge main

Dale uz jen vypracovavate kod a uploadujete do remote repozitare:
git commit -a -m"<popis commitu>"  a git push.
