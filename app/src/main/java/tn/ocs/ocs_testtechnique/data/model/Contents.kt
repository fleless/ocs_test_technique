package tn.ocs.ocs_testtechnique.data.model

data class Contents(
    val detaillink: String,
    val highlightid: Any,
    val id: String,
    val isbookmarkable: Boolean,
    val seasons: List<Season>,
    val selectid: Any,
    val pitch: String
)