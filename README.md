Pro vytvoreni vlastni branche postupujte nasledovne:
1) Aktualizovat lokalni obraz repozitare: `git pull`
2) Zalozit novou *lokalni* branche `git checkout -b username`
(*username* nahradte svym CVUT loginem)
3) Vytvorit branche na serveru `git push --set-upstream origin username`
4) Commitovat `git commit -a -m"new branch username"`
(option `-a` nahrazuje `git add` ktery se pred commitem musi delat)
5) Uploadovat zmenu `git push`

V prubehu hodiny uz pak staci opakovat krok 4) a 5), jen prosim do zpravy
commitu napiste o tom co commitujete (napr.: "implementace tridy Car" nebo 
"prvni cast cviceni").

Pro merge main branche do vlastni branche postupujte takto:
1) Aktualizovat lokalni obraz main branche:
`git checkout main` a pote `git pull`
2) Prepnuti do vlastni branche:
`git checkout username`
3) Merge (slouceni main branche s vasi vlastni branchi):
`git merge main`
4) Dale uz jen vypracovavate kod a uploadujete do remote repozitare:
`git commit -a -m"<popis commitu>"`  a `git push`.


Problemy s Gitem:
>  remote: GitLab: You are not allowed to push code to protected branches on this project.

Nejspis pushujes do main branche (kam nemas pravo). To je kvuli tomu
ze sis neprepnul branche.
Pro zjisteni aktivni (HEAD) branche dej `git branche` kde krizek indikuje aktivni branche.
Pro prepnuti branche zadej `git checkout username`.
Pokud jeste neexistuje postupuj podle navodu pro vytvoreni vlastni branche vyse.

>   Windows : Problem: je zapotrebi opakovane nastavovat SSH klic

https://stackoverflow.com/questions/18404272/running-ssh-agent-when-starting-git-bash-on-windows
