package com.meesho.task.githubpulls.data.models.api

import com.google.gson.annotations.SerializedName

data class PullRequests(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("diff_url") val diffUrl: String,
    @SerializedName("patch_url") val patchUrl: String,
    @SerializedName("issue_url") val issueUrl: String,
    @SerializedName("commits_url") val commitsUrl: String,
    @SerializedName("review_comments_url") val reviewCommentsUrl: String,
    @SerializedName("review_comment_url") val reviewCommentUrl: String,
    @SerializedName("comments_url") val commentsUrl: String,
    @SerializedName("statuses_url") val statusesUrl: String,
    @SerializedName("number") val number: Int,
    @SerializedName("state") val state: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("assignee") val assignee: Assignee,
    @SerializedName("labels") val labels: List<Label>,
    @SerializedName("milestone") val milestone: Milestone,
    @SerializedName("locked") val locked: Boolean,
    @SerializedName("active_lock_reason") val activeLockReason: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("closed_at") val closedAt: String,
    @SerializedName("merged_at") val mergedAt: String,
    @SerializedName("head") val head: Head,
    @SerializedName("base") val base: Base,
    @SerializedName("_links") val links: Links,
    @SerializedName("user") val user: User
)