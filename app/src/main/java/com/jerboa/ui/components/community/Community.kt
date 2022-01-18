package com.jerboa.ui.components.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jerboa.SortOptionsDialog
import com.jerboa.SortTopOptionsDialog
import com.jerboa.datatypes.CommunityView
import com.jerboa.datatypes.SortType
import com.jerboa.datatypes.sampleCommunityView
import com.jerboa.ui.components.common.LargerCircularIcon
import com.jerboa.ui.components.common.PictrsBannerImage
import com.jerboa.ui.theme.ACTION_BAR_ICON_SIZE
import com.jerboa.ui.theme.DRAWER_BANNER_SIZE
import com.jerboa.ui.theme.Muted

@Composable
fun CommunityTopSection(
    communityView: CommunityView,
    modifier: Modifier = Modifier,
    onClickFollowCommunity: (communityView: CommunityView) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            communityView.community.banner?.also {
                PictrsBannerImage(
                    url = it, modifier = Modifier.height(DRAWER_BANNER_SIZE)
                )
            }
            communityView.community.icon?.also {
                LargerCircularIcon(icon = it)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = communityView.community.title,
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "TODO",
                    tint = if (communityView.subscribed) {
                        Color.Green
                    } else {
                        Muted
                    },
                    modifier = Modifier.height(ACTION_BAR_ICON_SIZE)
                        .clickable { onClickFollowCommunity(communityView) }
                )
            }
            Row {
                Text(
                    text = "${communityView.counts.users_active_month} users / month",
                    style = MaterialTheme.typography.body1,
                    color = Muted,
                )
            }
        }
//            communityView.community.description?.also {
//                Text(
//                    text = it,
//                    style = MaterialTheme.typography.subtitle1
//                )
//            }
    }
}

@Preview
@Composable
fun CommunityTopSectionPreview() {
    CommunityTopSection(communityView = sampleCommunityView)
}

@Composable
fun CommunityHeader(
    communityName: String,
    onClickSortType: (SortType) -> Unit = {},
    selectedSortType: SortType,
    navController: NavController = rememberNavController(),
) {

    var showSortOptions by remember { mutableStateOf(false) }
    var showTopOptions by remember { mutableStateOf(false) }

    if (showSortOptions) {
        SortOptionsDialog(
            selectedSortType = selectedSortType,
            onDismissRequest = { showSortOptions = false },
            onClickSortType = {
                showSortOptions = false
                onClickSortType(it)
            },
            onClickSortTopOptions = {
                showSortOptions = false
                showTopOptions = !showTopOptions
            }
        )
    }

    if (showTopOptions) {
        SortTopOptionsDialog(
            selectedSortType = selectedSortType,
            onDismissRequest = { showTopOptions = false },
            onClickSortType = {
                showTopOptions = false
                onClickSortType(it)
            }
        )
    }

    TopAppBar(
        title = {
            CommunityHeaderTitle(
                communityName = communityName,
                selectedSortType = selectedSortType,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                showSortOptions = !showSortOptions
            }) {
                Icon(
                    Icons.Default.Sort,
                    contentDescription = "TODO",
                    tint = MaterialTheme.colors.onSurface
                )
            }
            IconButton(onClick = {
            }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "TODO",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    )
}

@Composable
fun CommunityHeaderTitle(
    communityName: String,
    selectedSortType: SortType,
) {
    Column {
        Text(
            text = communityName,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = selectedSortType.toString(),
            style = MaterialTheme.typography.body1,
            color = Muted,
        )
    }
}