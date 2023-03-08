package com.sedsoftware.nxmods.component.preferences.dsl

// Base item
sealed interface NxPreferenceItem {
    var key: NxPreferenceKeyUnique
}

// Controls
data class NxPreferenceButton(
    override var key: NxPreferenceKeyUnique = NxPreferenceKeyUnique.NONE
) : NxPreferenceItem

data class NxPreferenceSwitch(
    override var key: NxPreferenceKeyUnique = NxPreferenceKeyUnique.NONE,
    var value: Boolean = false
) : NxPreferenceItem

// Group
data class NxPreferenceGroup(
    var category: NxPreferenceCategory = NxPreferenceCategory.NONE,
    val items: MutableList<NxPreferenceItem> = mutableListOf()
)

// Root
data class NxPreferences(
    val groups: MutableList<NxPreferenceGroup> = mutableListOf()
)

fun NxPreferenceGroup.button(block: NxPreferenceButton.() -> Unit): NxPreferenceButton {
    val button = NxPreferenceButton()
    block(button)
    items.add(button)
    return button
}

fun NxPreferenceGroup.switch(block: NxPreferenceSwitch.() -> Unit): NxPreferenceSwitch {
    val switch = NxPreferenceSwitch()
    block(switch)
    items.add(switch)
    return switch
}

fun NxPreferences.group(block: NxPreferenceGroup.() -> Unit): NxPreferenceGroup {
    val group = NxPreferenceGroup()
    block(group)
    groups.add(group)
    return group
}

fun Any.nxPrefsRoot(block: NxPreferences.() -> Unit) =
    NxPreferences().apply(block)

fun NxPreferences.updateBoolean(key: NxPreferenceKeyUnique, value: Boolean): NxPreferences =
    copy(groups = groups.map { it.copy(items = it.items.updateSwitch(key, value)) }.toMutableList())

fun MutableList<NxPreferenceItem>.updateSwitch(key: NxPreferenceKeyUnique, value: Boolean): MutableList<NxPreferenceItem> =
    map { if (it is NxPreferenceSwitch && it.key == key) { it.copy(value = value) } else { it } }.toMutableList()
