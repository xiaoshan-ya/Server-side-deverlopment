# 基于pytest
# pytest test_spittr_firefox.py

import pytest
import allure
import time
import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities


# @pytest.mark.firefox
class TestSpittrFirefox():
    def setup_method(self, method):
        options = webdriver.FirefoxOptions()
        options.add_argument("--headless") # 设置火狐为headless无界面模式
        self.driver = webdriver.Firefox(options=options)
        self.driver.implicitly_wait(30)
        self.vars = {}

    def teardown_method(self, method):
        self.driver.quit()

    @allure.story('test for firefox')
    @allure.description("""
    这是使用firefox做的测试.
    """)
    def test_add_spittle(self):
        self.driver.get("http://www.spittr.com/spittr/login")

        self.driver.find_element(By.NAME, "username").send_keys("tzs919")
        self.driver.find_element(By.NAME, "password").send_keys("123456")
        self.driver.find_element(By.NAME, "submit").click()

        self.driver.find_element(By.LINK_TEXT, "Spittles").click()

        self.driver.find_element(By.NAME, "message").click()
        self.driver.find_element(By.NAME, "message").send_keys(
            "----" + time.strftime("%Y-%m-%d  %H:%M:%S", time.localtime()) + "----")

        self.driver.find_element(By.XPATH, "//input[@type='submit']").click()

        self.driver.find_element(By.LINK_TEXT, "Logout").click()

        assert self.driver.title == "Spitter"
        print(self.driver.title)
