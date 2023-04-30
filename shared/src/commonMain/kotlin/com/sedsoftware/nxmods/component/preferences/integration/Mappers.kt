package com.sedsoftware.nxmods.component.preferences.integration

import com.sedsoftware.nxmods.component.preferences.NxModsPreferences.Model
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.State


internal val stateToModel: (State) -> Model = {
    Model(
        preferences = it.preferences
    )
}
