package com.jerboa.datatypes.types

data class PersonMentionView(
    var person_mention: PersonMention,
    var comment: Comment,
    var creator: Person,
    var post: Post,
    var community: Community,
    var recipient: Person,
    var counts: CommentAggregates,
    var creator_banned_from_community: Boolean,
    var subscribed: SubscribedType /* "Subscribed" | "NotSubscribed" | "Pending" */,
    var saved: Boolean,
    var creator_blocked: Boolean,
    var my_vote: Int? = null,
)