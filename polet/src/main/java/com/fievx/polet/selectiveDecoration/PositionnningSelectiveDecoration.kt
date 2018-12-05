package com.fievx.polet.selectiveDecoration

interface PositionnningSelectiveDecoration: SelectiveDecoration {
    var includeFirst: Boolean
    var includeLast: Boolean
    var includeFirstInSameTypeGroup: Boolean
    var includeLastInSameTypeGroup: Boolean
    var includeInner: Boolean
    var includeInnerInTypeGroup: Boolean
}