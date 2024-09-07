/*
 * Copyright (c) 2024 Singularity Indonesia
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package validation

sealed interface ValidationError

data object InvalidFormat : ValidationError

data object InvalidEmpty : ValidationError
