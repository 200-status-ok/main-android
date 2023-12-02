@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class
)

package com.example.haminjast.ui.screen.createPoster

import android.graphics.drawable.Drawable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.R
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.network.loginretrofit.LoginRetrofit
import com.example.haminjast.data.network.loginretrofit.LoginRetrofitService
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.component.TagSelector
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.screen.common.TitleDescription
import com.example.haminjast.ui.screen.common.TitleDescriptionInput
import com.example.haminjast.ui.screen.createPoster.component.AddContactDialog
import com.example.haminjast.ui.screen.createPoster.component.AiSuggestionBar
import com.example.haminjast.ui.screen.createPoster.component.ContactsList
import com.example.haminjast.ui.screen.createPoster.component.CreatePosterBottomBar
import com.example.haminjast.ui.screen.createPoster.component.CreatePosterTopBar
import com.example.haminjast.ui.screen.createPoster.component.ImageSelector
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.util.RTLPixel5Previews
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreatePosterScreen(
    loginDataStore: LoginDataStore,
    viewModel: CreatePosterViewModel = viewModel(
        factory = provideViewModelFactory(
            loginDataStore,
            LoginRepository.getInstance(
                LoginRetrofit.getRetrofitInstance()
                    .create(LoginRetrofitService::class.java)
            )
        )
    ),
    onCloseClicked: () -> Unit = {},
) {


    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(35.7219, 51.3347)
        zoom = 12.0
    }
    val depokMarkerState = rememberMarkerState(
        geoPoint = GeoPoint(35.7219, 51.3347)
    )
    val showAddContactDialog by viewModel.showAddContactDialog.collectAsStateWithLifecycle()

    val title by viewModel.title.collectAsStateWithLifecycle()
    val desc by viewModel.desc.collectAsStateWithLifecycle()
    val posterStatus by viewModel.posterStatus.collectAsStateWithLifecycle()
    val contacts by viewModel.contacts.collectAsStateWithLifecycle()
    val imgUrls by viewModel.imgUrls.collectAsStateWithLifecycle()
    val tagFieldText by viewModel.tagFieldText.collectAsStateWithLifecycle()
    val suggestedTags by viewModel.suggestedTags.collectAsStateWithLifecycle()
    val selectedTags by viewModel.selectedTags.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current

    val screenWidth = remember {
        configuration.screenWidthDp
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        viewModel.addImageUrl(it.toString())
    }

    Scaffold(
        topBar = {
//            CreatePosterTopBar(onCloseClicked = onCloseClicked, posterStatus = posterStatus,
//                onLostClicked = {
//                    viewModel.setPosterStatus(PosterStatus.Lost)
//                }, onFoundClicked = {
//                    viewModel.setPosterStatus(PosterStatus.Found)
//                }
//            )
        },
        content = { innerPadding ->
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(
                            rememberScrollState()
                        )
                        .animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CreatePosterTopBar(onCloseClicked = onCloseClicked, posterStatus = posterStatus,
                        onLostClicked = {
                            viewModel.setPosterStatus(PosterStatus.Lost)
                        }, onFoundClicked = {
                            viewModel.setPosterStatus(PosterStatus.Found)
                        }
                    )
                    ImageSelector(
                        imgUrls = imgUrls,
                        onSelectorClicked = {
                            launcher.launch(PickVisualMediaRequest())
                        },
                        screenWidth = screenWidth
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    if (imgUrls.isNotEmpty()) {
                        AiSuggestionBar()
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    TitleDescriptionInput(
                        title = stringResource(id = R.string.title),
                        description = "یکی از مشکلاتی که مشکلان بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.", //todo
                        inputFieldHeight = 48.dp,
                        value = title,
                        onValueChanged = { viewModel.setTitle(it) }
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TitleDescriptionInput(
                        title = stringResource(id = R.string.description),
                        description = "یکی از مشکلاتی که مشکلان بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.", //todo
                        inputFieldHeight = 144.dp,
                        cursorAlignment = Alignment.TopStart,
                        value = desc,
                        onValueChanged = { viewModel.setDesc(it) }
                    )

                    Spacer(modifier = Modifier.size(16.dp))
                    TitleDescription(
                        title = stringResource(id = R.string.tag),
                        description = "یکی از مشکلاتی که مشکلان بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.", //todo
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    TagSelector(
                        modifier = Modifier.padding(horizontal = 20.dp), fieldText = tagFieldText,
                        onValueChanged = {
                            viewModel.setTagFieldText(it)
                        },
                        suggestedTags = suggestedTags,
                        selectedTags = selectedTags,
                        onSuggestedTagClicked = {
                            viewModel.setTagFieldText("")
                            viewModel.addSelectedTag(it)
                        },
                        onSelectedTagClicked = {
                            viewModel.removeSelectedTag(it)
                        }
                    )

                    Spacer(modifier = Modifier.size(16.dp))
                    TitleDescription(title = "موقعیت مکانی (برای انتخاب مکان نگه دارید)")
                    Spacer(modifier = Modifier.size(8.dp))
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .height(300.dp)
                            .border(
                                width = 1.5.dp,
                                color = PrimaryBlack.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        OpenStreetMap(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .height(350.dp),
                            cameraState = cameraState,
                            onMapLongClick = {
                                depokMarkerState.geoPoint = it
                            },
                            depokMarkerState = depokMarkerState
                        ) {
                            Marker(
                                state = depokMarkerState,
                                icon = LocalContext.current.getDrawable(R.drawable.ic_location)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp))
                    TitleDescription(title = stringResource(id = R.string.contacts))
                    Spacer(modifier = Modifier.size(8.dp))
                    ContactsList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        contacts = contacts,
                        onAddContactClicked = {
                            viewModel.setShowAddContactDialog(true)
                        },
                        onDeleteContactClicked = { contact ->
                            viewModel.removeContact(contact)
                        }
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.5.dp), color = PrimaryBlack.copy(alpha = 0.8f)
                    )
                    CreatePosterBottomBar(
                        onClickYes = {
                            viewModel.createPoster()
                        },
                        onClickNo = {
                            onCloseClicked()
                        }
                    )

                    if (showAddContactDialog) {
                        AddContactDialog(
                            onConfirmClicked = { name, value ->
                                viewModel.addContact(Contact(name, value))
                                viewModel.setShowAddContactDialog(false)
                            },
                            onDismissRequest = {
                                viewModel.setShowAddContactDialog(false)
                            }
                        )
                    }
                }
            }
        },
    )

}