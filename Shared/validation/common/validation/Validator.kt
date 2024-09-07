/*
 * Copyright (c) 2024 Singularity Indonesia
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package validation

import regex.EmailPattern

typealias Pattern = String
typealias Validator = Map<Pattern, ValidationError>

val EmailValidator =
    mapOf(
        EmailPattern to InvalidFormat,
        EmailPattern to InvalidEmpty,
    )
