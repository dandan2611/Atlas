# Atlas
###### Original par Osaris31 - Reprise de dandan2611

Il y a des agents `Agent`, qui peuvent rejoindre des agences `Agence`, qui proposent une liste de missions `Mission`, qui sont des actions à faire sur une liste de cibles `Cible`. Pour cela il faut développer une solution `Solution` qui donne le résultat attendu pour la liste de cibles de la mission. A chaque fois le problème est basé sur les données des cibles : elle contiennent leur nom, leur distance, leur connaissances des autres Cible, leur age et leur dangerosité.


Il faut créer une classe avec une méthode main.

Puis, pour initialiser l'Atlas, y entrer un Init:

`Atlas.demarrerAtlas();`


# Agents
Ensuite pour commencer, créez un Agent:
```
Agent agent = new Agent();
agent.nom = "Smith";
agent.prenom = "John";
```

Faites le rejoindre une agence:
```
agent.rejoindreAgence("Atlas");
```
Les agences correspondent au niveau de difficulté: chaque agence propose une liste de mission dans son niveau, et quand suffisament de ces missions ont été réussies on peut rejoindre l'agence suivante.

# Missions
## Sélection

Pour afficher les missions proposées, faire:
```
agent.afficherMissionsDisponibles();
```

Puis en choisir une avec 
```
agent.choisirMission("Cible prioritaire");
```

Ou par son niveau de difficulté

```
agent.choisirMission(1);
```

Cela affichera l'objectif de la mission choisit. Par exemple, "Pour cette mission, il y a deux cibles. Renvoyer le nom de la cible la plus proche des deux."

## Solution

Il faut donc coder une solution à cette mission, pour cela créer une classe SolutionMission1 qui implémente Solution, puis faire:

    Solution solution = new SolutionMission1();

    agent.accomplirMission(solution);
    
La solution doit implémenter une méthode public Object resoudre(Mission mission) qui retourne sous forme de String, int ou autre selon la mission, le resultat:

	@Override
	public Object resoudre(Mission mission) {
		if(mission.cibles[0].distance<mission.cibles[1].distance) {
			return mission.cibles[0].nom;
		}
		else {
			return mission.cibles[1].nom;
		}
	}

