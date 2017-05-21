[![Built with Spacemacs](https://cdn.rawgit.com/syl20bnr/spacemacs/442d025779da2f62fc86c2082703697714db6514/assets/spacemacs-badge.svg)](http://spacemacs.org)


# A command line tool "tree" written in ClojureScript using Lumo

Done in one of the Hack the Tower events, following Lambda Island [part one](https://lambdaisland.com/episodes/building-cli-apps-with-lumo-part-1) and [part two](https://lambdaisland.com/episodes/building-cli-apps-with-lumo-part-2).

## Development

To run locally, you need Node, npm and [Lumo](https://www.npmjs.com/package/lumo-cljs) installed.

Open a terminal and type `npm install` to get all the dependencies. 

Type `lumo` to get an instant cljs REPL. <i>Neat!</i>

Script to run the tool from a local directory: `lumo -c src -m birch.core <DIRECTORY>`
