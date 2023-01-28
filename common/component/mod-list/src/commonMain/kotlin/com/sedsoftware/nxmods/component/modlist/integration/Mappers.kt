package com.sedsoftware.nxmods.component.modlist.integration

import com.sedsoftware.nxmods.component.modlist.NxModsList.Model
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.State

internal val stateToModel: (State) -> Model = {
    Model(
        mods = it.mods,
        progressVisible = it.progressVisible,
    )
}
