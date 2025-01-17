/*
* Copyright 2014-2021 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
*/

package io.ktor.network.selector

import io.ktor.utils.io.core.*
import kotlinx.coroutines.*

public interface SelectorManager : CoroutineScope, Closeable {
    /**
     * Notifies the selector that selectable has been closed.
     */
    public fun notifyClosed(selectable: Selectable)

    /**
     * Suspends until [interest] is selected for [selectable]
     * May cause manager to allocate and run selector instance if not yet created.
     *
     * Only one selection is allowed per [interest] per [selectable] but you can
     * select for different interests for the same selectable simultaneously.
     * In other words you can select for read and write at the same time but should never
     * try to read twice for the same selectable.
     */
    public suspend fun select(
        selectable: Selectable,
        interest: SelectInterest
    )
}

/**
 * Select interest kind
 */
@Suppress("KDocMissingDocumentation", "NO_EXPLICIT_VISIBILITY_IN_API_MODE_WARNING")
public enum class SelectInterest {
    READ, WRITE, ACCEPT, CONNECT;

    public companion object {
        public val AllInterests: Array<SelectInterest>
            get() = values()
    }
}
