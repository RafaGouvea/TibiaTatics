package com.example.tibiatatics.model

class InbuementDataSource {

    companion object {

        fun createInbuementData(): ArrayList<InbuementModel>{

            val list = ArrayList<InbuementModel>()

            //suporte
            list.add(
                InbuementModel(
                    "Suporte",
                    "Featherweight (Aumento de Capacidade)",
                    "https://www.tibiawiki.com.br/images/d/d2/Featherweight_%28Aumento_de_Capacidade%29.gif",
                    "https://www.tibiawiki.com.br/images/1/1d/Fairy_Wings.gif",
                    "20 - Fairy Wings",
                    "https://www.tibiawiki.com.br/images/3/38/Little_Bowl_of_Myrrh.gif",
                    "10 - Little Bowl of Myrrh",
                    "https://www.tibiawiki.com.br/images/0/06/Goosebump_Leather.gif",
                    "5 - Goosebump Leather",
                )
            )

            list.add(
                InbuementModel(
                    "Suporte",
                    "Strike (Dano Critico)",
                    "https://www.tibiawiki.com.br/images/1/14/Strike_%28Dano_Cr%C3%ADtico%29.gif",
                    "https://www.tibiawiki.com.br/images/f/fb/Protective_Charm.gif",
                    "20 - Protective Charm",
                    "https://www.tibiawiki.com.br/images/f/f7/Sabretooth_%28Item%29.gif",
                    "25 - Sabretooth",
                    "https://www.tibiawiki.com.br/images/f/f7/Vexclaw_Talon.gif",
                    "5 - Vexclaw Talon",
                )
            )

            list.add(
                InbuementModel(
                    "Suporte",
                    "Swiftness (Skillboost de Velocidade)",
                    "https://www.tibiawiki.com.br/images/d/dd/Swiftness_%28Skillboost_de_Velocidade%29.gif",
                    "https://www.tibiawiki.com.br/images/8/84/Damselfly_Wing.gif",
                    "15 - Damselfly Wing",
                    "https://www.tibiawiki.com.br/images/1/1c/Compass.gif",
                    "25 - Compaass",
                    "https://www.tibiawiki.com.br/images/6/69/Waspoid_Wing.gif",
                    "20 - Waspoid Wing",
                )
            )

            list.add(
                InbuementModel(
                    "Suporte",
                    "Vampirism (Roubo de Vida)",
                    "https://www.tibiawiki.com.br/images/6/6e/Vampirism_%28Roubo_de_Vida%29.gif",
                    "https://www.tibiawiki.com.br/images/f/f1/Vampire_Teeth.gif",
                    "25 - Vampire Teeth",
                    "https://www.tibiawiki.com.br/images/1/1d/Bloody_Pincers.gif",
                    "15 - Bloody Pincers",
                    "https://www.tibiawiki.com.br/images/8/85/Piece_of_Dead_Brain.gif",
                    "5 - Piece of Dead Brain",
                )
            )

            list.add(
                InbuementModel(
                    "Suporte",
                    "Vibrancy (Remoção de Paralisia)",
                    "https://www.tibiawiki.com.br/images/6/62/Vibrancy_%28Remo%C3%A7%C3%A3o_de_Paralisia%29.gif",
                    "https://www.tibiawiki.com.br/images/0/0d/Wereboar_Hooves.gif",
                    "20 -Wereboar Hooves",
                    "https://www.tibiawiki.com.br/images/f/f8/Crystallized_Anger.gif",
                    "15 - Crystallized Anger",
                    "https://www.tibiawiki.com.br/images/f/fd/Quill.gif",
                    "5 - Quill",
                )
            )

            list.add(
                InbuementModel(
                    "Suporte",
                    "Void (Roubo de Mana)",
                    "https://www.tibiawiki.com.br/images/4/40/Void_%28Roubo_de_Mana%29.gif",
                    "https://www.tibiawiki.com.br/images/6/69/Rope_Belt.gif",
                    "25 - Rope Belt",
                    "https://www.tibiawiki.com.br/images/d/d6/Silencer_Claws.gif",
                    "25 - Silencer Claw",
                    "https://www.tibiawiki.com.br/images/8/8c/Some_Grimeleech_Wings.gif",
                    "5 - Some Grimeleech Wings",
                )
            )

            //skill
            list.add(
                InbuementModel(
                    "Aumento de Skill",
                    "Bash (Skillboost de Clava)",
                    "https://www.tibiawiki.com.br/images/c/cd/Bash_%28Skillboost_de_Clava%29.gif",
                    "https://www.tibiawiki.com.br/images/b/bd/Cyclops_Toe.gif",
                    "20 - Cyclops Toe",
                    "https://www.tibiawiki.com.br/images/9/9b/Ogre_Nose_Ring.gif",
                    "15 - Ogre Nose Ring",
                    "https://www.tibiawiki.com.br/images/4/48/Warmaster%27s_Wristguards.gif",
                    "10 - Warmaster's Wristguards",
                )
            )

            list.add(
                InbuementModel(
                    "Aumento de Skill",
                    "Blockade (Skillboost de Escudo)",
                    "https://www.tibiawiki.com.br/images/6/62/Blockade_%28Skillboost_de_Escudo%29.gif",
                    "https://www.tibiawiki.com.br/images/4/45/Piece_of_Scarab_Shell.gif",
                    "20 - Piece of Scarab Shell",
                    "https://www.tibiawiki.com.br/images/d/d9/Brimstone_Shell.gif",
                    "25 - Brimstone Shell",
                    "https://www.tibiawiki.com.br/images/d/d6/Frazzle_Skin.gif",
                    "25 - Frazzle Skin",
                )
            )

            list.add(
                InbuementModel(
                    "Aumento de Skill",
                    "Chop (Skillboost de Machado)",
                    "https://www.tibiawiki.com.br/images/2/2c/Chop_%28Skillboost_de_Machado%29.gif",
                    "https://www.tibiawiki.com.br/images/7/70/Orc_Tooth.gif",
                    "20 - Orc Tooth",
                    "https://www.tibiawiki.com.br/images/1/10/Battle_Stone.gif",
                    "25 - Battle Stone",
                    "https://www.tibiawiki.com.br/images/0/0f/Moohtant_Horn.gif",
                    "20 - Moohtant Horn",
                )
            )

            list.add(
                InbuementModel(
                    "Aumento de Skill",
                    "Slash (Skillboost de Espada)",
                    "https://www.tibiawiki.com.br/images/2/2a/Slash_%28Skillboost_de_Espada%29.gif",
                    "https://www.tibiawiki.com.br/images/0/04/Lion%27s_Mane.gif",
                    "25 - Lion's Mane",
                    "https://www.tibiawiki.com.br/images/b/b2/Mooh%27tah_Shell.gif",
                    "25 - Mooh'tah Shell",
                    "https://www.tibiawiki.com.br/images/4/41/War_Crystal.gif",
                    "5 - War Crystal",
                )
            )

            list.add(
                InbuementModel(
                    "Aumento de Skill",
                    "Epiphany (Skillboost de Nível Mágico)",
                    "https://www.tibiawiki.com.br/images/3/3d/Epiphany_%28Skillboost_de_N%C3%ADvel_M%C3%A1gico%29.gif",
                    "https://www.tibiawiki.com.br/images/a/a4/Elvish_Talisman.gif",
                    "25 - Elvish Talisman",
                    "https://www.tibiawiki.com.br/images/2/29/Broken_Shamanic_Staff.gif",
                    "15 - Broken Shamanic Staff",
                    "https://www.tibiawiki.com.br/images/5/57/Strand_of_Medusa_Hair.gif",
                    "15 - Strand of Medusa Hair",
                )
            )

            list.add(
                InbuementModel(
                    "Aumento de Skill",
                    "Precision (Skillboost de Distância)",
                    "https://www.tibiawiki.com.br/images/e/e6/Precision_%28Skillboost_de_Dist%C3%A2ncia%29.gif",
                    "https://www.tibiawiki.com.br/images/c/cf/Elven_Scouting_Glass.gif",
                    "25 - Elven Scouting Glass",
                    "https://www.tibiawiki.com.br/images/c/ca/Elven_Hoof.gif",
                    "20 - Elven Hoof",
                    "https://www.tibiawiki.com.br/images/c/cf/Metal_Spike.gif",
                    "10 - Metal Spike",
                )
            )



            return list

        }

    }

}