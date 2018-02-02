Ext.data.JsonP.dev_howtos_scayt({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='dev_howtos_scayt-section-spell-checker-and-spell-check-as-you-type-%28scayt%29'>Spell Checker and Spell Check As You Type (SCAYT)</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/dev_howtos_scayt-section-how-do-i-set-scayt-to-turn-on-automatically%3F'>How Do I Set SCAYT to Turn On Automatically?</a></li>\n<li>\n<a href='#!/guide/dev_howtos_scayt-section-how-do-i-disable-scayt-in-ckeditor%3F'>How Do I Disable SCAYT in CKEditor?</a></li>\n<li>\n<a href='#!/guide/dev_howtos_scayt-section-how-do-i-change-the-default-language-for-spell-check-as-you-type-%28scayt%29%3F'>How Do I Change the Default Language for Spell Check As You Type (SCAYT)?</a></li></ol>\n</div>\n\n<p>The following article contains tips about customizing the spell checker behavior in CKEditor. Please refer to the <a href=\"#!/guide/dev_spellcheck\">Spell Checking</a> article for more information about the spell checker.</p>\n\n<h2 id='dev_howtos_scayt-section-how-do-i-set-scayt-to-turn-on-automatically%3F'>How Do I Set SCAYT to Turn On Automatically?</h2>\n\n<p>If you want to turn on the <a href=\"#!/guide/dev_spellcheck-section-spell-check-as-you-type-%28scayt%29\">Spell Check As You Type (SCAYT)</a> feature in CKEditor by default, set the <a href=\"#!/api/CKEDITOR.config-cfg-scayt_autoStartup\" rel=\"CKEDITOR.config-cfg-scayt_autoStartup\" class=\"docClass\">CKEDITOR.config.scayt_autoStartup</a> configuration setting to <code>true</code>.</p>\n\n<pre><code>config.scayt_autoStartup = true;\n</code></pre>\n\n<h2 id='dev_howtos_scayt-section-how-do-i-disable-scayt-in-ckeditor%3F'>How Do I Disable SCAYT in CKEditor?</h2>\n\n<p>If you want to completely disable the <a href=\"#!/guide/dev_spellcheck-section-spell-check-as-you-type-%28scayt%29\">Spell Check As You Type (SCAYT)</a> feature in CKEditor, remove the <a href=\"https://ckeditor.com/cke4/addon/scayt\">SpellCheckAsYouType (SCAYT)</a> from your CKEditor build with CKBuilder or alternatively, disable the <code>scayt</code> plugin using the <a href=\"#!/api/CKEDITOR.config-cfg-removePlugins\" rel=\"CKEDITOR.config-cfg-removePlugins\" class=\"docClass\">CKEDITOR.config.removePlugins</a> configuration setting.</p>\n\n<pre><code>config.removePlugins = 'scayt';\n</code></pre>\n\n<p>If you want to leave SCAYT available, but prevent the feature from being turned on automatically on loading the editor, set the <a href=\"#!/api/CKEDITOR.config-cfg-scayt_autoStartup\" rel=\"CKEDITOR.config-cfg-scayt_autoStartup\" class=\"docClass\">CKEDITOR.config.scayt_autoStartup</a> configuration setting to <code>false</code>. This is the default value for CKEditor configuration.</p>\n\n<pre><code>config.scayt_autoStartup = false;\n</code></pre>\n\n<h2 id='dev_howtos_scayt-section-how-do-i-change-the-default-language-for-spell-check-as-you-type-%28scayt%29%3F'>How Do I Change the Default Language for Spell Check As You Type (SCAYT)?</h2>\n\n<p>By default <a href=\"#!/guide/dev_spellcheck-section-spell-check-as-you-type-%28scayt%29\">SCAYT</a> treats the text written in the editor as American English (<code>en_US</code>). If you want to change the default SCAYT language, set the <a href=\"#!/api/CKEDITOR.config-cfg-scayt_sLang\" rel=\"CKEDITOR.config-cfg-scayt_sLang\" class=\"docClass\">CKEDITOR.config.scayt_sLang</a> configuration option to one of the 16 possible language codes that are currently accepted.</p>\n\n<pre><code>// Sets SCAYT to French.\nconfig.scayt_sLang = 'fr_FR';\n</code></pre>\n\n<p>The following language codes are currently supported by SCAYT: <code>en_US, en_GB, pt_BR, da_DK, nl_NL, en_CA, fi_FI, fr_FR, fr_CA, de_DE, el_GR, it_IT, nb_NO, pt_PT, es_ES, and sv_SE</code>. If you enter a language code that is not supported, SCAYT will fall back to the default American English setting.</p>\n","title":"Spell Checker and SCAYT","meta_description":"Most frequently asked question and answers about the spell checker and SCAYT.","meta_keywords":"ckeditor, editor, wysiwyg, howto, howtos, faq, questions, answers, spell, checker, spellchecker, scayt, spellchecking, check, checking, spellcheck, thesaurus, mistake, mistakes, spelling, typo, correction, correct, configuration, configure, modify, modification, change, customize, customization, customise, customisation"});