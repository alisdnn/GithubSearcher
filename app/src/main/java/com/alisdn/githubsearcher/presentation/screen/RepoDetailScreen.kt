package com.alisdn.githubsearcher.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alisdn.githubsearcher.R
import com.alisdn.githubsearcher.presentation.state.StarBadgeState
import com.alisdn.githubsearcher.ui.TopAppBar
import com.alisdn.githubsearcher.ui.theme.gold
import com.alisdn.githubsearcher.ui.theme.space


@Composable
fun RepoDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    avatarUrl: String,
    repoTitle: String,
    repoDescription: String,
    stars: String,
    name: String,
    sharedState: State<StarBadgeState>,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(id = R.string.app_name)) { onBackClick.invoke() }
        },
        content = {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .then(modifier.padding(MaterialTheme.space.small))
            ) {

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    painter = rememberAsyncImagePainter(model = avatarUrl),
                    contentDescription = avatarUrl,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.space.large))


                Row {

                    Column {

                        Row {
                            Text(
                                text = stringResource(R.string.name),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                            Text(
                                text = name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }

                        Spacer(modifier = Modifier.height(MaterialTheme.space.small))

                        Row {
                            Text(
                                text = stringResource(R.string.repoTitle),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                            Text(
                                text = repoTitle,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }

                        Spacer(modifier = Modifier.height(MaterialTheme.space.small))

                        Row {
                            Text(
                                text = stringResource(R.string.stars),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                            Text(
                                text = stars,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.space.small))

                StarBadge(sharedState.value)

                Spacer(modifier = Modifier.height(MaterialTheme.space.medium))

                Row {
                    Text(
                        text = stringResource(R.string.repoDescription),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                    Text(
                        text = repoDescription,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
        }
    )
}

@Composable
fun StarBadge(sharedState: StarBadgeState) {

    when (sharedState) {
        is StarBadgeState.None -> {
            Text(
                text = stringResource(R.string.star_bagde_state_none),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        is StarBadgeState.Loading -> {
            Text(
                text = stringResource(R.string.calculating_forks_count),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        is StarBadgeState.Finished -> {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.forksCount),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                    Text(
                        text = sharedState.forks.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.space.medium))

                Row {
                    Spacer(modifier = Modifier.height(MaterialTheme.space.medium))

                    if (sharedState.forks > 5000) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = stringResource(
                                id = R.string.star_icon
                            ),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.space.medium))

                    }


                    Text(
                        text = if (sharedState.forks > 5000) stringResource(R.string.with_badge)
                        else stringResource(R.string.without_badge),
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (sharedState.forks > 5000) gold else Color.Red,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
