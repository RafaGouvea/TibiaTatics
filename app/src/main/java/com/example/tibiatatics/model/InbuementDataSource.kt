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

            //proteção elemental
            list.add(
                InbuementModel(
                    "Proteção Elemental",
                    "Cloud Fabric (Proteção de Energia)",
                    "https://www.tibiawiki.com.br/images/4/4f/Cloud_Fabric_%28Prote%C3%A7%C3%A3o_de_Energia%29.gif",
                    "https://www.tibiawiki.com.br/images/a/a3/Wyvern_Talisman.gif",
                    "20 - Wyvern Talisman",
                    "https://www.tibiawiki.com.br/images/b/be/Crawler_Head_Plating.gif",
                    "15 - Crawler Head Plating",
                    "https://www.tibiawiki.com.br/images/d/de/Wyrm_Scale.gif",
                    "10 - Wyrm Scale",
                )
            )

            list.add(
                InbuementModel(
                    "Proteção Elemental",
                    "Demon Presence (Proteção de Sagrado)",
                    "https://www.tibiawiki.com.br/images/6/6c/Demon_Presence_%28Prote%C3%A7%C3%A3o_de_Sagrado%29.gif",
                    "https://www.tibiawiki.com.br/images/3/38/Cultish_Robe.gif",
                    "25 - Cultish Robe",
                    "https://www.tibiawiki.com.br/images/4/4c/Cultish_Mask.gif",
                    "25 - Cultish Mask",
                    "https://www.tibiawiki.com.br/images/a/a3/Hellspawn_Tail.gif",
                    "20 - Hellspawn Tail",
                )
            )

            list.add(
                InbuementModel(
                    "Proteção Elemental",
                    "Dragon Hide (Proteção de Fogo)",
                    "https://www.tibiawiki.com.br/images/3/3b/Dragon_Hide_%28Prote%C3%A7%C3%A3o_de_Fogo%29.gif",
                    "https://www.tibiawiki.com.br/images/d/d9/Green_Dragon_Leather.gif",
                    "20 - Green Dragon Leather",
                    "https://www.tibiawiki.com.br/images/0/01/Blazing_Bone.gif",
                    "10 - Blazing Bone",
                    "https://www.tibiawiki.com.br/images/5/51/Draken_Sulphur.gif",
                    "5 - Draken Sulphur",
                )
            )

            list.add(
                InbuementModel(
                    "Proteção Elemental",
                    "Lich Shroud (Proteção de Morte)",
                    "https://www.tibiawiki.com.br/images/4/4a/Lich_Shroud_%28Prote%C3%A7%C3%A3o_de_Morte%29.gif",
                    "https://www.tibiawiki.com.br/images/c/c2/Flask_of_Embalming_Fluid.gif",
                    "25 - Flask of Embalming Fluid",
                    "https://www.tibiawiki.com.br/images/f/ff/Gloom_Wolf_Fur.gif",
                    "20 - Gloom Wolf Fur",
                    "https://www.tibiawiki.com.br/images/0/08/Mystical_Hourglass.gif",
                    "5 - Mystical Hourglass",
                )
            )

            list.add(
                InbuementModel(
                    "Proteção Elemental",
                    "Snake Skin (Proteção de Terra)",
                    "https://www.tibiawiki.com.br/images/2/27/Snake_Skin_%28Prote%C3%A7%C3%A3o_de_Terra%29.gif",
                    "https://www.tibiawiki.com.br/images/f/f6/Piece_of_Swampling_Wood.gif",
                    "25 - Piece of Swampling Wood",
                    "https://www.tibiawiki.com.br/images/3/3e/Snake_Skin.gif",
                    "20 - Snake Skin",
                    "https://www.tibiawiki.com.br/images/7/7a/Brimstone_Fangs.gif",
                    "10 - Brimstone Fangs",
                )
            )

            list.add(
                InbuementModel(
                    "Proteção Elemental",
                    "Quara Scale (Proteção de Gelo)",
                    "https://www.tibiawiki.com.br/images/2/23/Quara_Scale_%28Prote%C3%A7%C3%A3o_de_Gelo%29.gif",
                    "https://www.tibiawiki.com.br/images/f/fa/Winter_Wolf_Fur.gif",
                    "25 - Winter Wolf Fur",
                    "https://www.tibiawiki.com.br/images/f/fc/Thick_Fur.gif",
                    "15 - Tick Fur",
                    "https://www.tibiawiki.com.br/images/5/54/Deepling_Warts.gif",
                    "10 - Deepling Warts",
                )
            )

            //Dano elemental
            list.add(
                InbuementModel(
                    "Dano Elemental",
                    "Reap (Dano de Morte)",
                    "https://www.tibiawiki.com.br/images/b/b5/Reap_%28Dano_de_Morte%29.gif",
                    "https://www.tibiawiki.com.br/images/6/65/Pile_of_Grave_Earth.gif",
                    "25 - Pile of Grave Earth",
                    "https://www.tibiawiki.com.br/images/8/8f/Demonic_Skeletal_Hand.gif",
                    "20 - Demonic Skeletal Hand",
                    "https://www.tibiawiki.com.br/images/0/0f/Petrified_Scream.gif",
                    "5 - Petrified Scream",
                )
            )

            list.add(
                InbuementModel(
                    "Dano Elemental",
                    "Electrify (Dano de Energia)",
                    "https://www.tibiawiki.com.br/images/8/87/Electrify_%28Dano_de_Energia%29.gif",
                    "https://www.tibiawiki.com.br/images/b/b8/Rorc_Feather.gif",
                    "25 - Rorc Feather",
                    "https://www.tibiawiki.com.br/images/f/fc/Peacock_Feather_Fan.gif",
                    "5 - Peacock Feather Fan",
                    "https://www.tibiawiki.com.br/images/d/dc/Energy_Vein.gif",
                    "1 - Energy Vein",
                )
            )

            list.add(
                InbuementModel(
                    "Dano Elemental",
                    "Venom (Dano de Terra)",
                    "https://www.tibiawiki.com.br/images/b/b5/Venom_%28Dano_de_Terra%29.gif",
                    "https://www.tibiawiki.com.br/images/6/6f/Swamp_Grass.gif",
                    "25 - Swamp Grass",
                    "https://www.tibiawiki.com.br/images/b/b9/Poisonous_Slime.gif",
                    "20 - Poisonous Slime",
                    "https://www.tibiawiki.com.br/images/6/65/Slime_Heart.gif",
                    "2 - Deepling Warts",
                )
            )

            list.add(
                InbuementModel(
                    "Dano Elemental",
                    "Frost (Dano de Gelo)",
                    "https://www.tibiawiki.com.br/images/b/b7/Frost_%28Dano_de_Gelo%29.gif",
                    "https://www.tibiawiki.com.br/images/8/83/Frosty_Heart.gif",
                    "25 - Frosty Heart",
                    "https://www.tibiawiki.com.br/images/6/6e/Seacrest_Hair.gif",
                    "10 - Seacrest Hair",
                    "https://www.tibiawiki.com.br/images/3/3e/Polar_Bear_Paw.gif",
                    "5 - Polar Bear Paw",
                )
            )

            list.add(
                InbuementModel(
                    "Dano Elemental",
                    "Scorch (Dano de Fogo)",
                    "https://www.tibiawiki.com.br/images/d/dc/Scorch_%28Dano_de_Fogo%29.gif",
                    "https://www.tibiawiki.com.br/images/7/7d/Fiery_Heart.gif",
                    "25 - Fiery Heart",
                    "https://www.tibiawiki.com.br/images/a/ac/Green_Dragon_Scale.gif",
                    "5 - Green Dragon Scale",
                    "https://www.tibiawiki.com.br/images/b/b8/Demon_Horn.gif",
                    "5 - Demon Horn",
                )
            )

            return list

        }

    }

}