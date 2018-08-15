package ru.jjba.jr2.presentation.navigation.command

import ru.terrakok.cicerone.commands.Command

class StartFlow(val screenKey: String, val transitionData: Any?) : Command