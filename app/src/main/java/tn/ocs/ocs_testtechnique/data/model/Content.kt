package tn.ocs.ocs_testtechnique.data.model

import java.lang.reflect.Constructor

data class Content(
    val detaillink: String,
    val duration: String,
    val fullscreenimageurl: String,
    val highlefticon: Any,
    val highrighticon: Any,
    val id: String,
    val imageurl: String,
    val lowrightinfo: Any,
    val playinfoid: Playinfoid,
    val subtitle: String,
    val subtitlefocus: Any,
    val title: List<Title>
)
