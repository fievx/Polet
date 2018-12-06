package com.fievx.polet.decorationSelector

interface PositionnningSelectiveDecoration: DecorationSelector {
    var includeFirst: Boolean
    var includeLast: Boolean
    var includeFirstInSameTypeGroup: Boolean
    var includeLastInSameTypeGroup: Boolean
    var includeInner: Boolean
    var includeInnerInTypeGroup: Boolean
}