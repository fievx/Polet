package com.fievx.polet.selectiveDecoration

interface ViewTypeSelectiveDecoration : SelectiveDecoration {
    /**
     * Should add the specific view type to the list of excluded if not yet present
     */
    fun exclude(vararg viewTypes: Int)

    /**
     * Should add the specific view type to the list of included if not yet present
     */
    fun include(vararg viewTypes: Int)
}