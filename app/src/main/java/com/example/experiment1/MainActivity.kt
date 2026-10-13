package com.example.experiment1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.experiment1.ui.theme.Experiment1Theme

/**
 * 实验二首页：列出 4 个布局实验，点进去分别打开对应的 Activity。
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Experiment1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExperimentMenu(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/** 一个实验条目：标题、说明、以及点进去要启动的 Activity。 */
private data class Experiment(
    @param:StringRes val titleRes: Int,
    @param:StringRes val descRes: Int,
    val activityClass: Class<*>,
)

private val experiments = listOf(
    Experiment(
        R.string.exp_linear_title,
        R.string.exp_linear_desc,
        LinearLayoutActivity::class.java,
    ),
    Experiment(
        R.string.exp_table_title,
        R.string.exp_table_desc,
        TableLayoutActivity::class.java,
    ),
    Experiment(
        R.string.exp_constraint1_title,
        R.string.exp_constraint1_desc,
        ConstraintCalculatorActivity::class.java,
    ),
    Experiment(
        R.string.exp_constraint2_title,
        R.string.exp_constraint2_desc,
        SpaceTravelActivity::class.java,
    ),
)

@Composable
fun ExperimentMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp),
    ) {
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.home_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(20.dp))

        experiments.forEach { experiment ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        context.startActivity(Intent(context, experiment.activityClass))
                    },
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(experiment.titleRes),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = stringResource(experiment.descRes),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExperimentMenuPreview() {
    Experiment1Theme {
        ExperimentMenu()
    }
}
