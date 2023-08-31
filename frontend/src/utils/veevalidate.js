import { configure, defineRule} from 'vee-validate';
import AllRules from '@vee-validate/rules';
import ennames from '../assets/names/ennames.json';
import konames from '../assets/names/konames.json';
import { localize } from '@vee-validate/i18n';
import en from '@vee-validate/i18n/dist/locale/en.json';
import ko from '@vee-validate/i18n/dist/locale/ko.json';

// Install all rules
Object.keys(AllRules).forEach(rule => {
    defineRule(rule, AllRules[rule]);
});


configure({
  generateMessage: localize({
    en: {
      names: ennames.names,
      messages: en.messages,
    },
    ko: {
      names: konames.names,
      messages: ko.messages,
    },
  })
});