package com.meesho.task.githubpulls.data.models.api

import com.google.gson.annotations.SerializedName
data class Repo(
    @SerializedName("id") val id: Int,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("private") val private: Boolean,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("archive_url") val archiveUrl: String,
    @SerializedName("assignees_url") val assigneesUrl: String,
    @SerializedName("blobs_url") val blobsUrl: String,
    @SerializedName("branches_url") val branchesUrl: String,
    @SerializedName("clone_url") val cloneUrl: String,
    @SerializedName("collaborators_url") val collaboratorsUrl: String,
    @SerializedName("comments_url") val commentsUrl: String,
    @SerializedName("commits_url") val commitsUrl: String,
    @SerializedName("compare_url") val compareUrl: String,
    @SerializedName("contents_url") val contentsUrl: String,
    @SerializedName("contributors_url") val contributorsUrl: String,
    @SerializedName("deployments_url") val deploymentsUrl: String,
    @SerializedName("downloads_url") val downloadsUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("forks_url") val forksUrl: String,
    @SerializedName("git_commits_url") val gitCommitsUrl: String,
    @SerializedName("git_refs_url") val gitRefsUrl: String,
    @SerializedName("git_tags_url") val gitTagsUrl: String,
    @SerializedName("git_url") val gitUrl: String,
    @SerializedName("hooks_url") val hooksUrl: String,
    @SerializedName("issue_comment_url") val issueCommentUrl: String,
    @SerializedName("issue_events_url") val issueEventsUrl: String,
    @SerializedName("issues_url") val issuesUrl: String,
    @SerializedName("keys_url") val keysUrl: String,
    @SerializedName("labels_url") val labelsUrl: String,
    @SerializedName("languages_url") val languagesUrl: String,
    @SerializedName("merges_url") val mergesUrl: String,
    @SerializedName("milestones_url") val milestonesUrl: String,
    @SerializedName("mirror_url") val mirrorUrl: String,
    @SerializedName("notifications_url") val notificationsUrl: String,
    @SerializedName("pulls_url") val pullsUrl: String,
    @SerializedName("releases_url") val releasesUrl: String,
    @SerializedName("ssh_url") val sshUrl: String,
    @SerializedName("stargazers_url") val stargazersUrl: String,
    @SerializedName("statuses_url") val statusesUrl: String,
    @SerializedName("subscribers_url") val subscribersUrl: String,
    @SerializedName("subscription_url") val subscriptionUrl: String,
    @SerializedName("svn_url") val svnUrl: String,
    @SerializedName("tags_url") val tagsUrl: String,
    @SerializedName("teams_url") val teamsUrl: String,
    @SerializedName("trees_url") val treesUrl: String,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("language") val language: Any,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("default_branch") val defaultBranch: String,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("topics") val topics: List<String>,
    @SerializedName("has_issues") val hasIssues: Boolean,
    @SerializedName("has_wiki") val hasWiki: Boolean,
    @SerializedName("has_pages") val hasPages: Boolean,
    @SerializedName("has_downloads") val hasDownloads: Boolean,
    @SerializedName("archived") val archived: Boolean,
    @SerializedName("pushed_at") val pushedAt: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("permissions") val permissions: Permissions,
    @SerializedName("allow_rebase_merge") val allowRebaseMerge: Boolean,
    @SerializedName("allow_squash_merge") val allowSquashMerge: Boolean,
    @SerializedName("allow_merge_commit") val allowMergeCommit: Boolean,
    @SerializedName("subscribers_count") val subscribersCount: Int,
    @SerializedName("network_count") val networkCount: Int
)